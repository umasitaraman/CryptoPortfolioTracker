package com.cryptoportfolio.exceptions;

public class UnableToSaveToDatabaseException extends CryptoPortfolioException {

    private static final long serialVersionUID = -6616000570371503635L;

    public UnableToSaveToDatabaseException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public UnableToSaveToDatabaseException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public UnableToSaveToDatabaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UnableToSaveToDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
