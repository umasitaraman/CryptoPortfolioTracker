package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.converter.ModelConverter;
import com.cryptoportfolio.dynamodb.dao.TransactionDao;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.exceptions.TransactionsNotFoundException;
import com.cryptoportfolio.models.TransactionModel;
import com.cryptoportfolio.models.requests.GetTransactionsRequest;
import com.cryptoportfolio.models.responses.GetTransactionsResponse;
import com.cryptoportfolio.utils.Auth;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;

public class GetTransactionsActivity {

    private final Logger log = LogManager.getLogger();
    private TransactionDao transactionDao;
    private Gson gson;

    /**
     * Instantiates a new GetTransactionsActivity object.
     */

    @Inject
    public GetTransactionsActivity(TransactionDao transactionDao, Gson gson) {
        this.transactionDao = transactionDao;
        this.gson = gson;
    }

    /**
     * This method handles the incoming request by retrieving the transaction history from the database for the provided username.
     * <p>
     * It then returns the transaction history for a specific user / specific asset for the user.
     * <p>
     * If the transaction history does not exist, this should throw a TransactionHistoryNotFoundException.
     *
     * @param getTransactionsRequest request object containing the username and the AssetFlag
     * @return getTransactionsResponse result object containing the API defined {@link TransactionModel}
     */

    public GetTransactionsResponse execute (GetTransactionsRequest getTransactionsRequest) {
//        LambdaLogger logger = context.getLogger();
//        logger.log(gson.toJson(getTransactionsRequest));

        String username = getTransactionsRequest.getUsername();
        Auth.authenticateToken(username, getTransactionsRequest.getAuthToken());
        String assetFlag = getTransactionsRequest.getAssetFlag();

        List<Transaction> transactions = transactionDao.getTransactions(username, assetFlag);

        if (transactions == null) {
            throw new TransactionsNotFoundException("Resource not found : Could not find Transaction History");
        }

        List<TransactionModel> transactionModelList = new ModelConverter().toTransactionModelList(transactions);

        return GetTransactionsResponse.builder()
                .transactions(transactionModelList)
                .build();
    }
}
