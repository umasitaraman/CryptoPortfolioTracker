package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.cryptoportfolio.dynamodb.models.Portfolio;
import com.cryptoportfolio.exceptions.PortfolioNotFoundException;
import com.cryptoportfolio.exceptions.UnableToSaveToDatabaseException;

import javax.inject.Inject;

/**
 * The PortfolioDao class will create a new portfolio and also fetch an existing portfolio
 */
public class PortfolioDao {
    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public PortfolioDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     *
     * @param username Requires a UserName whose Portfolio needs to be fetched
     * @return Returns a Portfolio for the given User
     */
    public Portfolio getPortfolio(String username) {
        Portfolio portfolio = this.dynamoDBMapper.load(Portfolio.class, username);

        if (portfolio == null) {
            throw new PortfolioNotFoundException("[Not Found] Resource not found : Could not find portfolio for the user :  " + username);
        }
        return portfolio;
    }

    /**
     *
     * @param portfolio Saves a portfolio to the database
     * @return returns the saved portfolio
     */
    public void savePortfolio(Portfolio portfolio) {
        try {
            this.dynamoDBMapper.save(portfolio);
        } catch (Exception e){
            //logger.log(e.toString());
            throw new UnableToSaveToDatabaseException("[Internal Server Error] Failed : Unable to service request");
        }
    }
}
