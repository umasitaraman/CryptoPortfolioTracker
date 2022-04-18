package com.cryptoportfolio.exceptions;

public class InsufficientAssetsException extends CryptoPortfolioException {

    public InsufficientAssetsException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public InsufficientAssetsException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public InsufficientAssetsException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public InsufficientAssetsException(String message, Throwable cause) {
        super(message, cause);
    }

}
