package com.cryptoportfolio.models.requests;

import java.util.Objects;


/**
 * Builder class to create request to fetch a portfolio for the given user
 */
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPortfolioRequest {
    private String username;
    private String authToken;

//    public GetPortfolioRequest() {
//
//    }
//
//    public GetPortfolioRequest(Builder builder) {
//        this.username = builder.username;
//        this.authToken = builder.authToken;
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
//    public static Builder builder() { return new Builder(); }
//
//    public static final class Builder {
//        private String username;
//        private String authToken;
//
//        private Builder() {
//
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
//        public GetPortfolioRequest build() { return new GetPortfolioRequest(this); }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        GetPortfolioRequest that = (GetPortfolioRequest) o;
//        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAuthToken(), that.getAuthToken());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getAuthToken());
//    }
}
