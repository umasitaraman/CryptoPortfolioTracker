package activity;

import com.cryptoportfolio.activity.RegisterActivity;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.models.requests.RegisterRequest;
import com.cryptoportfolio.models.responses.RegisterResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterActivityTest {
    @Mock
    private UserDao userDao;
    @Mock
    private Gson gson;
    @InjectMocks
    private RegisterActivity registerActivity;
    TestContext context = new TestContext();
    private static final Logger logger = LoggerFactory.getLogger(RegisterActivityTest.class);

    @BeforeEach
    public void setUp() {
        initMocks(this);
        registerActivity = new RegisterActivity(userDao, gson);
    }

    @Test
    public void handleRequest_provideCorrectFormatUsernameAndPassword_registersUserSuccessfully() {
        // GIVEN
        logger.info("Invoke RegisterActivityTest");
        String username = "testing";
        String password = "testing";
        RegisterRequest request = new RegisterRequest(username, password);

        // WHEN
        doNothing().when(userDao).createUser(isA(User.class));
        RegisterResponse response = registerActivity.handleRequest(request, context);

        //THEN
        assertEquals(username, response.getUsername());
    }

    @Test
    public void handleRequest_provideInCorrectFormatUsername_throwsIllegalArgumentException() {
        // GIVEN
        String username = "tes%$#@#ting";
        String password = "testing";


        RegisterRequest request = new RegisterRequest(username, password);

        // WHEN
        doNothing().when(userDao).createUser(isA(User.class));

        //THEN
        assertThrows(IllegalArgumentException.class, () -> registerActivity.handleRequest(request, context));
    }

    @Test
    public void handleRequest_provideInCorrectFormatPassword_throwsIllegalArgumentException() {
        // GIVEN
        String username = "testing";
        String password = "tes";


        RegisterRequest request = new RegisterRequest(username, password);

        // WHEN
        doNothing().when(userDao).createUser(isA(User.class));

        //THEN
        assertThrows(IllegalArgumentException.class, () -> registerActivity.handleRequest(request, context));
    }
}
