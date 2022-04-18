package com.cryptoportfolio.exceptions;

public class UserAlreadyExistsException extends CryptoPortfolioException {

    private static final long serialVersionUID = -7514608374992098226L;

    /**
     * Exception with no message or cause.
     */
    public UserAlreadyExistsException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
