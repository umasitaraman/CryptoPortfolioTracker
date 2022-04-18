package com.cryptoportfolio.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dynamodb.dao.UserDao;
import com.cryptoportfolio.models.requests.VerifyRequest;
import com.cryptoportfolio.models.responses.VerifyResponse;
import com.cryptoportfolio.utils.Auth;
import com.google.gson.Gson;

import javax.inject.Inject;

public class VerifyActivity {

    private UserDao userDao;
    private Gson gson;

    @Inject
    public VerifyActivity(UserDao userDao, Gson gson) {
        this.userDao = userDao;
        this.gson = gson;
    }

    public VerifyResponse execute (VerifyRequest verifyRequest) {

//        LambdaLogger logger = context.getLogger();
//        logger.log(gson.toJson(verifyRequest));

        String username = verifyRequest.getUsername();
        String token = verifyRequest.getAuthToken();

        Auth.authenticateToken(username, token);

        return VerifyResponse.builder()
                .username(username)
                .authToken(token)
                .build();
    }
}
