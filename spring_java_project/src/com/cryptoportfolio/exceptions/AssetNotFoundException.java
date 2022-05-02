package com.cryptoportfolio.exceptions;

public class AssetNotFoundException extends CryptoPortfolioException {

    private static final long serialVersionUID = -8094038823757030313L;

    public AssetNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public AssetNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public AssetNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public AssetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
