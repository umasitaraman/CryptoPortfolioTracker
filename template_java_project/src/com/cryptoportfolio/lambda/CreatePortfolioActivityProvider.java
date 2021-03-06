package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cryptoportfolio.dependency.DaggerServiceComponent;
import com.cryptoportfolio.exceptions.CryptoPortfolioException;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.responses.CreatePortfolioResponse;

public class CreatePortfolioActivityProvider implements RequestHandler<CreatePortfolioRequest, CreatePortfolioResponse> {

    public CreatePortfolioActivityProvider() {

    }

    @Override
    public CreatePortfolioResponse handleRequest(final CreatePortfolioRequest createPortfolioRequest, Context context) {
        try {
            return DaggerServiceComponent.create()
                    .provideCreatePortfolioActivity()
                    .handleRequest(createPortfolioRequest, context);
        } catch (CryptoPortfolioException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("[Internal Server Error] Unable to service Request");
        }
    }

}
