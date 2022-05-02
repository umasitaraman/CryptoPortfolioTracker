package com.cryptoportfolio.controllers;

import com.cryptoportfolio.App;
import com.cryptoportfolio.activity.*;
import com.cryptoportfolio.dependency.ServiceComponent;
import com.cryptoportfolio.dynamodb.models.Transaction;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.exceptions.MissingFieldException;
import com.cryptoportfolio.models.requests.*;
import com.cryptoportfolio.models.responses.LoginResponse;
import com.cryptoportfolio.models.responses.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    private static final ServiceComponent component = App.component;

    @PostMapping(value = "/register", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> RegisterActivity(@Valid @RequestBody RegisterRequest registerRequest) {

        RegisterActivity registerActivity = component.provideRegisterActivity();

        RegisterResponse response = registerActivity.execute(registerRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> LoginActivity(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("Login");
        LoginActivity loginActivity = component.provideLoginActivity();
        LoginResponse response = loginActivity.execute(loginRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/portfolio/{id}", produces = {"application/json"})
    public ResponseEntity<?> getPortfolioActivity(@PathVariable String id, @RequestHeader ("cp-auth-token") String authToken) {
        GetPortfolioActivity getPortfolioActivity = component.provideGetPortfolioActivity();
        GetPortfolioRequest getPortfolioRequest = GetPortfolioRequest.builder()
                .username(id)
                .authToken(authToken)
                .build();

        return new ResponseEntity<>(getPortfolioActivity.execute(getPortfolioRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/portfolio/{id}", produces = {"application/json"})
    public ResponseEntity<?> createPortfolioActivity(@PathVariable String id,
                                                     @RequestHeader ("cp-auth-token") String authToken,
                                                     @Valid @RequestBody CreatePortfolioRequest createRequest) {
        CreatePortfolioActivity createPortfolioActivity = component.provideCreatePortfolioActivity();
        CreatePortfolioRequest createPortfolioRequest = CreatePortfolioRequest.builder()
                .username(createRequest.getUsername())
                .authToken(authToken)
                .assetQuantityMap(createRequest.getAssetQuantityMap())
                .transactions(createRequest.getTransactions())
                .build();

        return new ResponseEntity<>(createPortfolioActivity.execute(createPortfolioRequest), HttpStatus.OK);
    }

    @PutMapping (value = "/portfolio/{id}", produces = {"application/json"})
    public ResponseEntity<?> updatePortfolioActivity(@PathVariable String id,
                                                     @RequestHeader ("cp-auth-token") String authToken,
                                                     @Valid @RequestBody UpdatePortfolioRequest updateRequest) {
        UpdatePortfolioActivity updatePortfolioActivity = component.provideUpdatePortfolioActivity();
        UpdatePortfolioRequest updatePortfolioRequest = UpdatePortfolioRequest.builder()
                .username(updateRequest.getUsername())
                .authToken(authToken)
                .assetQuantityMap(updateRequest.getAssetQuantityMap())
                .transactions(updateRequest.getTransactions())
                .build();

        return new ResponseEntity<>(updatePortfolioActivity.execute(updatePortfolioRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/transactions/{id}", produces = {"application/json"})
    public ResponseEntity<?> getTransactionActivity(@PathVariable String id,
                                                    @RequestParam String assetFlag,
                                                    @RequestHeader ("cp-auth-token") String authToken) {
        GetTransactionsActivity getTransactionActivity = component.provideGetTransactionsActivity();
        GetTransactionsRequest getTransactionsRequest = GetTransactionsRequest.builder()
                .username(id)
                .authToken(authToken)
                .assetFlag(assetFlag)
                .build();

        return new ResponseEntity<>(getTransactionActivity.execute(getTransactionsRequest), HttpStatus.OK);
    }
}
