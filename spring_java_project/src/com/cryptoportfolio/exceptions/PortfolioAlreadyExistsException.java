package com.cryptoportfolio.exceptions;

public class PortfolioAlreadyExistsException extends CryptoPortfolioException {

    private static final long serialVersionUID = -7949257669437613643L;

    public PortfolioAlreadyExistsException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public PortfolioAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public PortfolioAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public PortfolioAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
