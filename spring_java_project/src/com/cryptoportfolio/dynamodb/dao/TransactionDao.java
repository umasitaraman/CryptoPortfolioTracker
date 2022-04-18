package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.exceptions.MissingFieldException;
import com.cryptoportfolio.exceptions.UnableToSaveToDatabaseException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The TransactionHistoryDao class will create a new Transaction History and also fetch an existing Transaction History
 */
public class TransactionDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public TransactionDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     *
     * @param assetFlag Requires an assetFlag, which is either ALL or an assetId
     * @return Returns a Portfolio for the given assetFlag
     */
    public List<Transaction> getTransactions(String username, String assetFlag) {
        if (assetFlag == null) {
            throw new MissingFieldException("[Bad Request] Asset Flag cannot be null");
        } else if (assetFlag.equalsIgnoreCase("ALL")) {
            Transaction event = new Transaction();
            event.setUsername(username);
            DynamoDBQueryExpression<Transaction> queryExpression = new DynamoDBQueryExpression<Transaction>()
                    .withHashKeyValues(event)
                    .withConsistentRead(false);
            return new ArrayList<>(dynamoDBMapper.query(Transaction.class, queryExpression));
        }

        Transaction event = new Transaction();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":username", new AttributeValue().withS(username));
        valueMap.put(":asset_id", new AttributeValue().withS(assetFlag));
        DynamoDBQueryExpression<Transaction> queryExpression = new DynamoDBQueryExpression<Transaction>()
                .withIndexName(Transaction.USERNAME_ASSET_ID_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("username = :username and asset_id = :asset_id")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Transaction> transactionList = dynamoDBMapper.query(Transaction.class, queryExpression);
        return transactionList;
    }

    /**
     *
     * @param transactions Saves a transactionHistory to the database
     *
     */

    public void batchSaveTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            try {
                dynamoDBMapper.save(transaction);
            } catch (Exception e) {
                throw new UnableToSaveToDatabaseException("[Internal Server Error] Failed : Unable to service request");
            }
        }
    }
}
