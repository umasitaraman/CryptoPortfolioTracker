package com.cryptoportfolio.exceptions;

public class CryptoPortfolioException extends RuntimeException {

    private static final long serialVersionUID = 8645139362142502053L;

    public CryptoPortfolioException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public CryptoPortfolioException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptoPortfolioException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public CryptoPortfolioException(String message, Throwable cause) {
        super(message, cause);
    }
}
