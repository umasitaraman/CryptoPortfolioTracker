//package activity;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.cryptoportfolio.activity.GetTransactionsActivity;
//import com.cryptoportfolio.converter.ModelConverter;
//import com.cryptoportfolio.dynamodb.dao.TransactionDao;
//import com.cryptoportfolio.dynamodb.models.Transaction;
//import com.cryptoportfolio.dynamodb.models.User;
//import com.cryptoportfolio.exceptions.TransactionsNotFoundException;
//import com.cryptoportfolio.models.TransactionModel;
//import com.cryptoportfolio.models.requests.GetTransactionsRequest;
//import com.cryptoportfolio.models.responses.GetTransactionsResponse;
//import com.cryptoportfolio.utils.Auth;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.stubbing.Answer;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//public class GetTransactionsActivityTest {
//    @Mock
//    private TransactionDao transactionDao;
//    @Mock
//    private Gson gson;
//
//    TestContext context = new TestContext();
//    String username = "testUsername";
//    private static final int TOKEN_DURATION = 3_600_000;
//    @InjectMocks
//    private GetTransactionsActivity getTransactionsActivity;
//    String token;
//
//    @BeforeEach
//    public void setUp() {
//        initMocks(this);
//        getTransactionsActivity = new GetTransactionsActivity(transactionDao, gson);
//        Algorithm algorithm = Algorithm.HMAC256("crypto-wizards");
//
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer("cryptoportfolio")
//                .withClaim("username", username)
//                .build();
//
//        Date expiry = new Date();
//        expiry.setTime(expiry.getTime() + TOKEN_DURATION);
//        token = JWT.create()
//                .withIssuer("cryptoportfolio")
//                .withClaim("username", username)
//                .withExpiresAt(expiry)
//                .sign(algorithm);
//    }
//
//    @Test
//    public void handleRequest_withAssetFlagAll_getsAllTheTransactionsForTheUser() {
//        // GIVEN
//        String username = "testUser";
//        String password = "testUser";
//        Map<String, Double> assetQuantityMap = new HashMap<>();
//        assetQuantityMap.put("bitcoin" , 3.0);
//        assetQuantityMap.put("fantom" , 5.0);
//        String assetFlag = "ALL";
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setIsNewUser(true);
//
//        GetTransactionsRequest request = GetTransactionsRequest.builder()
//                .username(username)
//                .authToken(token)
//                .assetFlag(assetFlag)
//                .build();
//
//        List<Transaction> transactions = new ArrayList<>();
//        double usdValue = 50.0;
//
//        for (String assetId : assetQuantityMap.keySet()) {
//            Transaction transaction = new Transaction();
//
//            transaction.setUsername(username);
//            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
//            transaction.setAssetId(assetId);
//            transaction.setAssetQuantity(assetQuantityMap.get(assetId));
//            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
//            transaction.setTransactionType("BUY");
//            transactions.add(transaction);
//
//            transaction = new Transaction();
//
//            transaction.setUsername(username);
//            transaction.setTransactionDate("2020-11-11T17:21:07.5272333Z");
//            transaction.setAssetId(assetId);
//            transaction.setAssetQuantity(1.0);
//            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
//            transaction.setTransactionType("SELL");
//            transactions.add(transaction);
//        }
//
//        List<TransactionModel> transactionModelList = new ModelConverter().toTransactionModelList(transactions);
//
//        //WHEN
//        when(transactionDao.getTransactions(username, assetFlag)).thenReturn(transactions);
//
//        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
//            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
//            GetTransactionsResponse response = getTransactionsActivity.handleRequest(request, context);
//            authMock.verify(() -> Auth.authenticateToken(username, token));
//            assertEquals(response.getTransactions(), transactionModelList);
//        }
//    }
//    @Test
//    public void handleRequest_withAssetFlagAsAssetId_getsAllTheTransactionsForParticularAssertIdForTheUser() {
//        // GIVEN
//        String username = "testUser";
//        String password = "testUser";
//        Map<String, Double> assetQuantityMap = new HashMap<>();
//        assetQuantityMap.put("bitcoin" , 3.0);
//        assetQuantityMap.put("fantom" , 5.0);
//        String assetFlag = "bitcoin";
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setIsNewUser(true);
//
//        GetTransactionsRequest request = GetTransactionsRequest.builder()
//                .username(username)
//                .authToken(token)
//                .assetFlag(assetFlag)
//                .build();
//
//        List<Transaction> transactions = new ArrayList<>();
//        double usdValue = 50.0;
//
//        for (String assetId : assetQuantityMap.keySet()) {
//
//            if (assetId.equalsIgnoreCase(assetFlag)) {
//                Transaction transaction = new Transaction();
//
//                transaction.setUsername(username);
//                transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
//                transaction.setAssetId(assetId);
//                transaction.setAssetQuantity(assetQuantityMap.get(assetId));
//                transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
//                transaction.setTransactionType("BUY");
//                transactions.add(transaction);
//
//                transaction = new Transaction();
//
//                transaction.setUsername(username);
//                transaction.setTransactionDate("2020-11-11T17:21:07.5272333Z");
//                transaction.setAssetId(assetId);
//                transaction.setAssetQuantity(1.0);
//                transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
//                transaction.setTransactionType("SELL");
//                transactions.add(transaction);
//            }
//        }
//
//        List<TransactionModel> transactionModelList = new ModelConverter().toTransactionModelList(transactions);
//
//        //WHEN
//        when(transactionDao.getTransactions(username, assetFlag)).thenReturn(transactions);
//
//        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
//            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
//            GetTransactionsResponse response = getTransactionsActivity.handleRequest(request, context);
//            authMock.verify(() -> Auth.authenticateToken(username, token));
//            assertEquals(response.getTransactions(), transactionModelList);
//        }
//    }
//
//    @Test
//    public void handleRequest_withNullTransactions_throwsTransactionsNotFoundException() {
//        // GIVEN
//        String username = "testUser";
//        String password = "testUser";
//        Map<String, Double> assetQuantityMap = new HashMap<>();
//        assetQuantityMap.put("bitcoin" , 3.0);
//        assetQuantityMap.put("fantom" , 5.0);
//        String assetFlag = "bitcoin";
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setIsNewUser(true);
//
//        GetTransactionsRequest request = GetTransactionsRequest.builder()
//                .username(username)
//                .authToken(token)
//                .assetFlag(assetFlag)
//                .build();
//
//        //WHEN
//        when(transactionDao.getTransactions(username, assetFlag)).thenReturn(null);
//
//        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
//            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
//            assertThrows(TransactionsNotFoundException.class, () -> getTransactionsActivity.handleRequest(request, context));
//        }
//    }
//}
