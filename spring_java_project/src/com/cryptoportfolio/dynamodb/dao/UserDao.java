package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.LoginException;
import com.cryptoportfolio.exceptions.UserAlreadyExistsException;

import javax.inject.Inject;

public class UserDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void createUser(User user) {
        if (null != dynamoDBMapper.load(User.class, user.getUsername())) {
            throw new UserAlreadyExistsException(String.format("[Bad Request] Registration Failed : user %s already exists", user.getUsername()));
        } else {
            dynamoDBMapper.save(user);
        }
    }

    public void updateUser(User user) {
        User updateUser = user;
        if (null != dynamoDBMapper.load(User.class, user.getUsername())) {
            updateUser.setIsNewUser(false);
            dynamoDBMapper.save(user);
        } else {
            throw new LoginException(String.format("[Unauthorized] Login Failed : user does not exist"));
        }
    }

    public User getUser(String username) {

        User user = dynamoDBMapper.load(User.class, username);
        if (null == user) {
            throw new LoginException(String.format("[Unauthorized] Login Failed : user %s does not exist", username));
        }

        return user;
    }
}
