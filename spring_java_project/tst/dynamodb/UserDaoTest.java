package dynamodb;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.DynamoDbClientProvider;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.LoginException;
import com.cryptoportfolio.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {
    private DynamoDBMapper dynamoDBMapper;
    private UserDao userDao;
    Map<String, Double> assetQuantityMap = new HashMap<>();
    String username;
    String password;
    boolean isNewUser;

    @BeforeEach
    public void setUp() {
        username = "backendtestuser";
        password = "backendtestuser";
        isNewUser = true;
        dynamoDBMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
        userDao = new UserDao(dynamoDBMapper);
    }

    @AfterEach
    private void teardown() {
        deleteTestData();
    }

    @Test
    public void createUser_savesUserToDatabase() {
        //GIVEN
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(isNewUser);

        //WHEN
        userDao.createUser(user);
        User dbUser = userDao.getUser(username);

        //THEN
        assertEquals(user, dbUser, "Matching User Found");
    }

    @Test
    public void createUser_userAlreadyExist_throwsUserAlreadyExistsException() {

        //GIVEN
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(isNewUser);

        //WHEN
        userDao.createUser(user);

        //THEN
        assertThrows(UserAlreadyExistsException.class, () -> userDao.createUser(user));
    }

    @Test
    public void updateUser_userAlreadyExist_updatesIsNewUserToFalse() {

        //GIVEN
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(isNewUser);

        //WHEN
        userDao.createUser(user);
        userDao.updateUser(user);

        User dbUser = userDao.getUser(username);

        //THEN
        assertEquals(dbUser.getIsNewUser(), false);
    }

    @Test
    public void updateUser_userDoesNotExist_throwsLoginException() {

        //GIVEN
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(isNewUser);

        //WHEN & THEN
        assertThrows(LoginException.class, () -> userDao.updateUser(user));
    }

    @Test
    public void getUser_userExists_fetchesTheUser() {

        //GIVEN
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(isNewUser);

        //WHEN
        userDao.createUser(user);
        User dbUser = userDao.getUser(username);

        //THEN
        assertEquals(user, dbUser, "Matching User Found");
    }

    @Test
    public void getUser_userDoesNotExist_throwsLoginException() {

        //GIVEN
        String username = "invalidUser";

        //WHEN & THEN
        assertThrows(LoginException.class, () -> userDao.getUser(username));
    }


    private void deleteTestData() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsNewUser(isNewUser);
        dynamoDBMapper.delete(user);
    }
}
