package com.cryptoportfolio.controllers.errorController;

import com.cryptoportfolio.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class CustomControllerAdvice {
    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ErrorResponse> handleMissingFieldException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(UnableToSaveToDatabaseException.class)
    public ResponseEntity<ErrorResponse> handleUnableToSaveToDatabaseException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(AssetNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleAssetNotAvailableException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(PortfolioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePortfolioNotFoundException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(TransactionsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTransactionsNotFoundException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            Exception e
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ),
                status
        );
    }

}
