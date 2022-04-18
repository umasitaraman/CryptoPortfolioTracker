package com.cryptoportfolio.converter;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.models.PortfolioModel;
import com.cryptoportfolio.models.TransactionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ModelConverter {
    /**
     * Converts a provided {@link Portfolio} into a {@link String} representation.
     * @param username the user for whom we need to create the portfolio
     * @param portfolio the List of UserAssets to convert
     * @return the converted Portfolio
     */
    public PortfolioModel toPortfolioModel(String username, Portfolio portfolio) {

        Map<String, Double> portfolioAssetsMap = portfolio.getAssetQuantityMap();

        return PortfolioModel.builder()
                .username(username)
                .assetQuantityMap(portfolioAssetsMap)
                .build();
    }

    public TransactionModel toTransactionModel(Transaction transaction) {

        return TransactionModel.builder()
                .username(transaction.getUsername())
                .transactionDate(transaction.getTransactionDate())
                .assetId(transaction.getAssetId())
                .assetQuantity(transaction.getAssetQuantity() != null ? transaction.getAssetQuantity() : 0.0)
                .transactionValue(transaction.getTransactionValue() != null ? transaction.getTransactionValue() : 0.0)
                .transactionType(transaction.getTransactionType())
                .build();
    }

    public List<TransactionModel> toTransactionModelList (List<Transaction> transactions) {
        List<TransactionModel> transactionsList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionsList.add(toTransactionModel(transaction));
        }
        return transactionsList;
    }

}
