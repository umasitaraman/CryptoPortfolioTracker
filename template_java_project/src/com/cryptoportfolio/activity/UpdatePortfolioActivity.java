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
import com.cryptoportfolio.exceptions.AssetNotAvailableException;
import com.cryptoportfolio.exceptions.UnableToSaveToDatabaseException;
import com.cryptoportfolio.models.requests.UpdatePortfolioRequest;
import com.cryptoportfolio.models.responses.UpdatePortfolioResponse;
import com.cryptoportfolio.settings.Settings;
import com.cryptoportfolio.utils.Auth;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdatePortfolioActivity implements RequestHandler<UpdatePortfolioRequest, UpdatePortfolioResponse> {

    private final Logger log = LogManager.getLogger();
    private PortfolioDao portfolioDao;
    private UserDao userDao;
    private TransactionDao transactionDao;
    private Gson gson;


    /**
     * Instantiates a new UpdatePortfolioActivity object.
     *
     */

    @Inject
    public UpdatePortfolioActivity(PortfolioDao portfolioDao, UserDao userDao, TransactionDao transactionDao, Gson gson) {
        this.userDao = userDao;
        this.portfolioDao = portfolioDao;
        this.transactionDao = transactionDao;
        this.gson = gson;
    }

    /**
     * This method handles the incoming request by updating the existing Portfolio
     * with the provided new asset quantities.
     * <p>
     * It then returns the newly updated Portfolio.
     * <p>
     *
     * @param updatePortfolioRequest request object containing the username and the asset,quantity map
     *                              associated with it
     * @return updatePortfolioResult result object containing the API defined {@link String}
     */
    @Override
    public UpdatePortfolioResponse handleRequest(final UpdatePortfolioRequest updatePortfolioRequest, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(gson.toJson(updatePortfolioRequest));

        Auth.authenticateToken(updatePortfolioRequest.getUsername(), updatePortfolioRequest.getAuthToken());

        List<Transaction> transactionList = new ArrayList<Transaction>(updatePortfolioRequest.getTransactions());

        Portfolio portfolio = new Portfolio();
        Map<String, Double> assetQuantityMap = updatePortfolioRequest.getAssetQuantityMap();

        if (!Settings.AVAILABLE_ASSETS.containsAll(assetQuantityMap.keySet())) {
            throw new AssetNotAvailableException("[Not Found] Resource not found : Asset(s) unavailable");
        }

        portfolio.setUsername(updatePortfolioRequest.getUsername());

        Map<String, Double> nonZeroAssetQuantityMap = new HashMap<>();

        for (String assetID : assetQuantityMap.keySet()) {
            if (assetQuantityMap.get(assetID) != 0) {
                nonZeroAssetQuantityMap.put(assetID, assetQuantityMap.get(assetID));
            }
        }

        logger.log(transactionList.toString());
        transactionDao.batchSaveTransactions(transactionList);

        portfolio.setAssetQuantityMap(nonZeroAssetQuantityMap);

        try {
            portfolioDao.savePortfolio(portfolio);
        } catch (DynamoDBMappingException e) {
            throw new UnableToSaveToDatabaseException("[Internal Server Error] Failed : Unable to service request");
        }

        return UpdatePortfolioResponse.builder()
                .message("Portfolio updated")
                .build();
    }

}
