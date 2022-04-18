package com.cryptoportfolio.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.dao.TransactionDao;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.AssetNotAvailableException;
import com.cryptoportfolio.exceptions.UnableToSaveToDatabaseException;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;
import com.cryptoportfolio.settings.Settings;
import com.cryptoportfolio.utils.Auth;
import com.google.gson.Gson;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatePortfolioActivity {

    private PortfolioDao portfolioDao;
    private UserDao userDao;
    private TransactionDao transactionDao;
    private Gson gson;

    /**
     * Instantiates a new CreatePortfolioActivity object.
     *
     */
    @Inject
    public CreatePortfolioActivity(PortfolioDao portfolioDao, UserDao userDao, TransactionDao transactionDao, Gson gson) {
        this.userDao = userDao;
        this.portfolioDao = portfolioDao;
        this.transactionDao = transactionDao;
        this.gson = gson;
    }


    /**
     * This method handles the incoming request by persisting a new Portfolio
     * with the provided username and the assetId,quantity map from the request.
     * <p>
     * It then returns the newly created Portfolio.
     * <p>
     *
     * @param createPortfolioRequest request object containing the username and the assetId,quantity map
     *                              associated with it
     * @return createPortfolioResult result object containing the API defined {@link String}
     */

    public CreatePortfolioResponse execute (final CreatePortfolioRequest createPortfolioRequest) {
        //LambdaLogger logger = context.getLogger();
        //logger.log(gson.toJson(createPortfolioRequest));
        List<Transaction> transactionList = new ArrayList<>(createPortfolioRequest.getTransactions());

        Auth.authenticateToken(createPortfolioRequest.getUsername(), createPortfolioRequest.getAuthToken());

        Portfolio portfolio = new Portfolio();
        Map<String, Double> assetQuantityMap = createPortfolioRequest.getAssetQuantityMap();

        if (!Settings.AVAILABLE_ASSETS.containsAll(assetQuantityMap.keySet())) {
            throw new AssetNotAvailableException("[Not Found] Resource not found : Asset(s) unavailable");
        }

        User user = userDao.getUser(createPortfolioRequest.getUsername());

        user.setIsNewUser(false);
        userDao.updateUser(user);

        portfolio.setUsername(createPortfolioRequest.getUsername());

        Map<String, Double> nonZeroAssetQuantityMap = new HashMap<>();

        for (String assetID : assetQuantityMap.keySet()) {
            if (assetQuantityMap.get(assetID) != 0) {
                nonZeroAssetQuantityMap.put(assetID, assetQuantityMap.get(assetID));
            }
        }
        transactionDao.batchSaveTransactions(transactionList);

        portfolio.setAssetQuantityMap(nonZeroAssetQuantityMap);

        try {
            portfolioDao.savePortfolio(portfolio);
        } catch (DynamoDBMappingException e) {
            //logger.log(e.toString());
            throw new UnableToSaveToDatabaseException("[Internal Server Error] Failed : Unable to service request");
        }

        return CreatePortfolioResponse.builder()
                .message("Portfolio created successfully")
                .build();
    }

}
