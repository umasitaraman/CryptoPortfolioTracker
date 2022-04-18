package com.cryptoportfolio.models.requests;

import com.cryptoportfolio.dynamodb.models.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The Builder class to create a portfolio request using the provided username and the assetId, quantity mapping
 */
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransactionsRequest {
    private String username;
    private String authToken;
    private String assetFlag;

//    public GetTransactionsRequest() {
//    }
//
//    public GetTransactionsRequest(Builder builder) {
//        this.username = builder.username;
//        this.authToken = builder.authToken;
//        this.assetFlag = builder.assetFlag;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getAuthToken() {
//        return authToken;
//    }
//
//    public void setAuthToken(String authToken) {
//        this.authToken = authToken;
//    }
//
//    public String getAssetFlag() {
//        return assetFlag;
//    }
//
//    public void setAssetFlag(String assetFlag) {
//        this.assetFlag = assetFlag;
//    }
//
//    public static Builder builder() { return new Builder(); }
//
//    public static final class Builder {
//        private String username;
//        private String authToken;
//        private String assetFlag;
//
//        private Builder() {
//        }
//
//        public Builder withUsername(String usernameToUse) {
//            this.username = usernameToUse;
//            return this;
//        }
//
//        public Builder withAuthToken(String authTokenToUse) {
//            this.authToken = authTokenToUse;
//            return this;
//        }
//
//        public Builder withAssetFlag(String assetFlagToUse) {
//            this.assetFlag = assetFlagToUse;
//            return this;
//        }
//
//        public GetTransactionsRequest build() {
//            return new GetTransactionsRequest(this);
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof GetTransactionsRequest)) return false;
//        GetTransactionsRequest that = (GetTransactionsRequest) o;
//        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAuthToken(), that.getAuthToken()) && Objects.equals(getAssetFlag(), that.getAssetFlag());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getAuthToken(), getAssetFlag());
//    }
//
//    @Override
//    public String toString() {
//        return "GetTransactionsRequest{" +
//                "username='" + username + '\'' +
//                ", authToken='" + authToken + '\'' +
//                ", assetFlag='" + assetFlag + '\'' +
//                '}';
//    }
}
