package com.cryptoportfolio.exceptions;

public class PortfolioNotFoundException extends CryptoPortfolioException {

    public PortfolioNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public PortfolioNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public PortfolioNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public PortfolioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
