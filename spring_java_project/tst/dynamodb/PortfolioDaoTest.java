//package dynamodb;
//
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.cryptoportfolio.dynamodb.DynamoDbClientProvider;
//import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
//import com.cryptoportfolio.dynamodb.models.Portfolio;
//import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
//import com.cryptoportfolio.exceptions.UnableToSaveToDatabaseException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class PortfolioDaoTest {
//    private DynamoDBMapper dynamoDBMapper;
//    private PortfolioDao portfolioDao;
//    Map<String, Double> assetQuantityMap = new HashMap<>();
//    String username;
//
//    @BeforeEach
//    public void setUp() {
//        username = "testuser";
//        assetQuantityMap.put("bitcoin" , 3.0);
//        assetQuantityMap.put("fantom" , 5.0);
//        dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
//        portfolioDao = new PortfolioDao(dynamoDBMapper);
//    }
//
//    @AfterEach
//    private void teardown() {
//        deleteTestData();
//    }
//
//    @Test
//    public void savePortfolio_savesPortfolioToDatabase() {
//
//        //GIVEN
//        Portfolio portfolio = new Portfolio();
//        portfolio.setUsername(username);
//        portfolio.setAssetQuantityMap(assetQuantityMap);
//
//        //WHEN
//        portfolioDao.savePortfolio(portfolio);
//        Portfolio dbPortfolio = portfolioDao.getPortfolio(username);
//
//        //THEN
//        assertEquals(portfolio, dbPortfolio, "Matching Portfolio Found");
//    }
//
//    @Test
//    public void getPortfolio_ExistingPortfolio_retrievesFromDatabase() {
//
//        //GIVEN
//        Portfolio portfolio = new Portfolio();
//        portfolio.setUsername(username);
//        portfolio.setAssetQuantityMap(assetQuantityMap);
//
//        //WHEN
//        portfolioDao.savePortfolio(portfolio);
//        Portfolio dbPortfolio = portfolioDao.getPortfolio(username);
//
//        //THEN
//        assertEquals(portfolio, dbPortfolio, "Matching Portfolio Found");
//    }
//
//    @Test
//    public void getPortfolio_InvalidPortfolio_throwsPortfolioNotFoundException() {
//
//        //GIVEN
//        username = "invalidUser";
//
//        //WHEN & THEN
//        assertThrows(PortfolioNotFoundException.class, () -> portfolioDao.getPortfolio(username));
//    }
//
//    @Test
//    public void savePortfolio_nullPortfolio_throwsUnableToSaveToDatabaseException() {
//
//        //GIVEN
//        Portfolio portfolio = new Portfolio();
//
//        //WHEN & THEN
//        assertThrows(UnableToSaveToDatabaseException.class, () -> portfolioDao.savePortfolio(portfolio));
//    }
//
//    private void deleteTestData() {
//        Portfolio portfolio = new Portfolio();
//        portfolio.setUsername("testuser");
//        portfolio.setAssetQuantityMap(assetQuantityMap);
//        dynamoDBMapper.delete(portfolio);
//    }
//}
