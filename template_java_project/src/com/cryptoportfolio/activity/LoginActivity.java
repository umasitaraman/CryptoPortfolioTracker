package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.LoginException;
import com.cryptoportfolio.models.requests.LoginRequest;
import com.cryptoportfolio.models.responses.LoginResponse;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.Date;

public class LoginActivity implements RequestHandler<LoginRequest, LoginResponse> {

    // Length of time auth token will remain valid in milliseconds
    private static final int TOKEN_DURATION = 3_600_000;

    private UserDao userDao;
    private Gson gson;

    @Inject
    public LoginActivity(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }

    @Override
    public LoginResponse handleRequest(LoginRequest loginRequest, Context context) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();


        if (null == username || "".equals(username) || null == password || "".equals(password)) {
            throw new LoginException("[Unauthorized] Login Failed : username and password required");
        }

        // Get user, throw exception if user does not exist
        User user = userDao.getUser(username);
        boolean isNewUser = user.getIsNewUser();

        // Check provided password against password from database
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new LoginException("[Forbidden] Login Failed: incorrect password");
        }

        // Create and sign JSON web token
        Date expiry = new Date();
        expiry.setTime(expiry.getTime() + TOKEN_DURATION);
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
        String token = JWT.create()
                .withIssuer("cryptoportfolio")
                .withClaim("username", username)
                .withExpiresAt(expiry)
                .sign(algorithm);

        return LoginResponse.builder()
                .username(username)
                .authToken(token)
                .isNewUser(isNewUser)
                .build();
    }
}
