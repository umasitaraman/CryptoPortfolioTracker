package com.cryptoportfolio.exceptions;

public class MissingFieldException extends CryptoPortfolioException {

    public MissingFieldException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public MissingFieldException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public MissingFieldException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public MissingFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
