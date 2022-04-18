package com.cryptoportfolio.utils;

import java.util.Objects;

public class VerificationStatus {

    private boolean verified;
    private String message;

    public VerificationStatus() {}

    public VerificationStatus(Builder builder) {
        this.verified = builder.verified;
        this.message = builder.message;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationStatus that = (VerificationStatus) o;
        return isVerified() == that.isVerified() && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isVerified(), getMessage());
    }

    @Override
    public String toString() {
        return "VerificationStatus{" +
                "isVerified=" + verified +
                ", message='" + message + '\'' +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private boolean verified;
        private String message;

        public Builder withVerified(boolean verified) {
            this.verified = verified;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public VerificationStatus build() {
            return new VerificationStatus(this);
        }
    }
}
