//package activity;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.cryptoportfolio.activity.GetPortfolioActivity;
//import com.cryptoportfolio.dynamodb.dao.AssetDao;
//import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
//import com.cryptoportfolio.dynamodb.models.Portfolio;
//import com.cryptoportfolio.dynamodb.models.User;
//import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
//import com.cryptoportfolio.models.PortfolioModel;
//import com.cryptoportfolio.models.requests.GetPortfolioRequest;
//import com.cryptoportfolio.models.responses.GetPortfolioResponse;
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
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//public class GetPortfolioActivityTest {
//    @Mock
//    private PortfolioDao portfolioDao;
//    @Mock
//    private Gson gson;
//    @Mock
//    private AssetDao assetDao;
//
//    TestContext context = new TestContext();
//    String username = "testUsername";
//    private static final int TOKEN_DURATION = 3_600_000;
//    @InjectMocks
//    private GetPortfolioActivity getPortfolioActivity;
//    String token;
//
//    @BeforeEach
//    public void setUp() {
//        initMocks(this);
//        getPortfolioActivity = new GetPortfolioActivity(portfolioDao, assetDao, gson);
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
//    //Happy Case
//    @Test
//    public void handleRequest_withProperUsername_getsTheCorrectPortfolio() {
//        // GIVEN
//        String username = "testUser";
//        String password = "testUser";
//        Map<String, Double> assetQuantityMap = new HashMap<>();
//        assetQuantityMap.put("bitcoin" , 3.0);
//        assetQuantityMap.put("fantom" , 5.0);
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setIsNewUser(true);
//
//        GetPortfolioRequest request = GetPortfolioRequest.builder()
//                .username(username)
//                .authToken(token)
//                .build();
//
//        Portfolio portfolio = new Portfolio();
//        portfolio.setUsername(username);
//        portfolio.setAssetQuantityMap(assetQuantityMap);
//
//        PortfolioModel portfolioModel = PortfolioModel.builder()
//                                        .assetQuantityMap(assetQuantityMap)
//                                        .username(username)
//                                        .build();
//
//        //WHEN
//        when(portfolioDao.getPortfolio(username)).thenReturn(portfolio);
//
//        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
//            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
//            GetPortfolioResponse response = getPortfolioActivity.handleRequest(request, context);
//            authMock.verify(() -> Auth.authenticateToken(username, token));
//            assertEquals(response.getPortfolio(), portfolioModel);
//        }
//    }
//    @Test
//    public void handleRequest_withUnavailableAsset_throwsAssetNotAvailableException() {
//        // GIVEN
//        String username = "testUser";
//        String password = "testUser";
//        Map<String, Double> assetQuantityMap = new HashMap<>();
//        assetQuantityMap.put("bitcoin" , 3.0);
//        assetQuantityMap.put("fantom" , 5.0);
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setIsNewUser(true);
//
//        GetPortfolioRequest request = GetPortfolioRequest.builder()
//                .username(username)
//                .authToken(token)
//                .build();
//
//        //WHEN
//        when(portfolioDao.getPortfolio(username)).thenReturn(null);
//        try (MockedStatic<Auth> authMock = Mockito.mockStatic(Auth.class)) {
//            authMock.when(() -> Auth.authenticateToken(username, token)).thenAnswer((Answer<Void>) invocation -> null);
//            assertThrows(PortfolioNotFoundException.class, () -> getPortfolioActivity.handleRequest(request, context));
//        }
//    }
//}
