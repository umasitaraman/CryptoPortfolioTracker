package dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.DynamoDbClientProvider;
import com.cryptoportfolio.dynamodb.dao.TransactionDao;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.exceptions.UnableToSaveToDatabaseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionDaoTest {
    private DynamoDBMapper dynamoDBMapper;
    private TransactionDao transactionDao;
    Map<String, Double> assetQuantityMap = new HashMap<>();
    String username;

    @BeforeEach
    public void setUp() {
        username = "testuser";
        assetQuantityMap.put("bitcoin" , 3.0);
        assetQuantityMap.put("fantom" , 5.0);
        dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
        transactionDao = new TransactionDao(dynamoDBMapper);

    }

    @AfterEach
    private void teardown() {
        deleteTestData();
    }

    @Test
    public void batchSaveTransactions_savesTransactionsToDatabase() {

        //GIVEN
        String username = "backendTestUser";
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("bitcoin" , 3.0);
        String assetFlag = "ALL";
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        Date date = new Date();

        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(assetQuantityMap.get(assetId));
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);

            transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2020-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(1.0);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("SELL");
            transactions.add(transaction);
        }

        transactionDao.batchSaveTransactions(transactions);

        List<Transaction> transactionList = transactionDao.getTransactions(username, assetFlag);
        System.out.println(transactionList.size());
        System.out.println(transactions.size());

        assertEquals(transactionList.size(), transactions.size());

    }

    @Test
    public void batchSaveTransactions_nullTransaction_throwsUnableToSaveToDatabaseException() {

        //GIVEN
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction());
        transactions.add(new Transaction());

        //WHEN & THEN
        assertThrows(UnableToSaveToDatabaseException.class, () -> transactionDao.batchSaveTransactions(transactions));
    }

    @Test
    public void getTransactionsWithAssetFlagALL_getAllTransactionsFromDatabaseForTheUser() {

        //GIVEN
        String username = "backendTestUser";
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("bitcoin" , 3.0);
        String assetFlag = "ALL";
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        Date date = new Date();

        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(assetQuantityMap.get(assetId));
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);
            transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2020-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(1.0);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("SELL");
            transactions.add(transaction);
        }

        transactionDao.batchSaveTransactions(transactions);

        List<Transaction> transactionList = transactionDao.getTransactions(username, assetFlag);

        assertEquals(transactionList.size(), transactions.size());

    }

    @Test
    public void getTransactionsWithAssetFlagAssetId_getTransactionsFromDatabaseForTheUser() {

        //GIVEN
        String username = "backendTestUser";
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("bitcoin" , 3.0);
        String assetFlag = "bitcoin";
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        Date date = new Date();

        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(assetQuantityMap.get(assetId));
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);
            transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2020-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(1.0);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("SELL");
            transactions.add(transaction);
        }

        List<Transaction> transactionList = transactionDao.getTransactions(username, assetFlag);

        assertEquals(transactionList.size(), transactions.size());

    }

    private void deleteTestData() {
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("bitcoin" , 3.0);
        String assetFlag = "ALL";
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        Date date = new Date();

        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(assetQuantityMap.get(assetId));
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);
            transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2020-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setAssetQuantity(1.0);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("SELL");
            transactions.add(transaction);
        }
        for (Transaction transaction : transactions) {
            dynamoDBMapper.delete(transaction);
        }
    }
}
