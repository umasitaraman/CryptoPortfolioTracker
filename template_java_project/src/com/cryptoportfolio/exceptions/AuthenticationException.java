package com.cryptoportfolio.exceptions;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public AuthenticationException(String message) {
        super("Authentication failed: " + message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public AuthenticationException(String message, Throwable cause) {
        super("Authentication failed: " + message, cause);
    }
}