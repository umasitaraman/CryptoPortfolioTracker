package com.cryptoportfolio.models.responses;

import java.util.Objects;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyResponse {

    private String username;
    private String authToken;

//    public VerifyResponse(Builder builder) {
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
//    public static Builder builder() {return new Builder();}
//
//    public static final class Builder {
//        public String username;
//        private String authToken;
//
//        public Builder withUsername(String username) {
//            this.username = username;
//            return this;
//        }
//
//        public Builder withToken(String authToken) {
//            this.authToken = authToken;
//            return this;
//        }
//
//        public VerifyResponse build() {return new VerifyResponse(this);}
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        VerifyResponse that = (VerifyResponse) o;
//        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAuthToken(), that.getAuthToken());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getAuthToken());
//    }
//
//    @Override
//    public String toString() {
//        return "VerifyResponse{" +
//                "username='" + username + '\'' +
//                ", authToken='" + authToken + '\'' +
//                '}';
//    }
}
