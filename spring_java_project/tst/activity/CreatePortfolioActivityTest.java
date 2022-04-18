package activity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.cryptoportfolio.activity.CreatePortfolioActivity;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.dynamodb.dao.TransactionDao;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.AssetNotAvailableException;
import com.cryptoportfolio.exceptions.LoginException;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;
import com.cryptoportfolio.utils.Auth;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreatePortfolioActivityTest {
    @Mock
    private PortfolioDao portfolioDao;
    @Mock
    private Gson gson;
    @Mock
    private UserDao userDao;
    @Mock
    private TransactionDao transactionDao;

    TestContext context = new TestContext();
    String username = "testUsername";
    private static final int TOKEN_DURATION = 3_600_000;
    @InjectMocks
    private CreatePortfolioActivity createPortfolioActivity;
    String token;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createPortfolioActivity = new CreatePortfolioActivity(portfolioDao, userDao, transactionDao, gson);
        Algorithm algorithm = Algorithm.HMAC256("crypto-wizards");

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("cryptoportfolio")
                .withClaim("username", username)
                .build();

        Date expiry = new Date();
        expiry.setTime(expiry.getTime() + TOKEN_DURATION);
        token = JWT.create()
                .withIssuer("cryptoportfolio")
                .withClaim("username", username)
                .withExpiresAt(expiry)
                .sign(algorithm);
    }

    //Happy Case
    @Test
    public void handleRequest_withValidUsername_createsPortfolio() {
        // GIVEN
        String username = "testUser";
        String password = "testUser";
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("bitcoin" , 3.0);
        assetQuantityMap.put("fantom" , 5.0);
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(true);
        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);
        }

        CreatePortfolioRequest request = CreatePortfolioRequest.builder()
                .assetQuantityMap(assetQuantityMap)
                .username(username)
                .authToken(token)
                .transactions(transactions).build();

        Portfolio portfolio = new Portfolio();
        portfolio.setUsername(username);
        portfolio.setAssetQuantityMap(assetQuantityMap);

        //WHEN
        when(userDao.getUser(username)).thenReturn(user);
        when(userDao.getUser(username)).thenReturn(user);
        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
            CreatePortfolioResponse response = createPortfolioActivity.handleRequest(request, context);
            authMock.verify(() -> Auth.authenticateToken(username, token));
        }
        //THEN
        verify(transactionDao).batchSaveTransactions(transactions);
        verify(portfolioDao).savePortfolio(portfolio);
    }

    @Test
    public void handleRequest_withInValidUsername_throwsLoginException() {
        // GIVEN
        String username = "testUser";
        String password = "testUser";
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("bitcoin" , 3.0);
        assetQuantityMap.put("fantom" , 5.0);
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(true);
        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);
        }

        CreatePortfolioRequest request = CreatePortfolioRequest.builder()
                .assetQuantityMap(assetQuantityMap)
                .username(username)
                .authToken(token)
                .transactions(transactions).build();

        //WHEN & THEN
        when(userDao.getUser(username)).thenThrow(new LoginException());
        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
            assertThrows(LoginException.class, () -> createPortfolioActivity.handleRequest(request, context));
        }
    }

    @Test
    public void handleRequest_withUnavailableAsset_throwsAssetNotAvailableException() {
        // GIVEN
        String username = "testUser";
        String password = "testUser";
        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("abc" , 3.0);
        assetQuantityMap.put("fantom" , 5.0);
        List<Transaction> transactions = new ArrayList<>();
        double usdValue = 50.0;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(true);
        for (String assetId : assetQuantityMap.keySet()) {
            Transaction transaction = new Transaction();
            transaction.setUsername(username);
            transaction.setTransactionDate("2016-11-11T17:21:07.5272333Z");
            transaction.setAssetId(assetId);
            transaction.setTransactionValue(assetQuantityMap.get(assetId) * usdValue);
            transaction.setTransactionType("BUY");
            transactions.add(transaction);
        }

        CreatePortfolioRequest request = CreatePortfolioRequest.builder()
                .assetQuantityMap(assetQuantityMap)
                .username(username)
                .authToken(token)
                .transactions(transactions).build();

        Portfolio portfolio = new Portfolio();
        portfolio.setUsername(username);
        portfolio.setAssetQuantityMap(assetQuantityMap);

        //WHEN
        when(userDao.getUser(username)).thenReturn(user);
        when(userDao.getUser(username)).thenReturn(user);
        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
            assertThrows(AssetNotAvailableException.class, () -> createPortfolioActivity.handleRequest(request, context));
        }
    }
}
