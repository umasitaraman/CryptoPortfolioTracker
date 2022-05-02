package com.cryptoportfolio.controllers.errorController;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String errorMessage;
    public ErrorResponse() {

    }

    public ErrorResponse(
            String errorMessage
    ) {
        this();

        this.errorMessage = errorMessage;
    }
}