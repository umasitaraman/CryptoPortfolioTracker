package com.cryptoportfolio.exceptions;

public class TransactionsNotFoundException extends CryptoPortfolioException {

    private static final long serialVersionUID = 5895295420839662082L;

    public TransactionsNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public TransactionsNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public TransactionsNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public TransactionsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
