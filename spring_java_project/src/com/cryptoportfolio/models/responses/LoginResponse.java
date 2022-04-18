package com.cryptoportfolio.models.responses;

import java.util.Objects;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String username;
    private String authToken;
    private boolean isNewUser;

//    public LoginResponse(Builder builder) {
//        this.username = builder.username;
//        this.authToken = builder.authToken;
//        this.isNewUser = builder.isNewUser;
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
//    public boolean isNewUser() {
//        return isNewUser;
//    }
//
//    public void setNewUser(boolean newUser) {
//        isNewUser = newUser;
//    }
//
//    public static Builder builder() {return new Builder();}
//
//    public static final class Builder {
//        public String username;
//        private String authToken;
//        private boolean isNewUser;
//
//        public Builder withUsername(String username) {
//            this.username = username;
//            return this;
//        }
//
//        public Builder withAuthToken(String authToken) {
//            this.authToken = authToken;
//            return this;
//        }
//
//        public Builder withIsNewUser(boolean isNewUser) {
//            this.isNewUser = isNewUser;
//            return this;
//        }
//
//        public LoginResponse build() {return new LoginResponse(this);}
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof LoginResponse)) return false;
//        LoginResponse that = (LoginResponse) o;
//        return isNewUser() == that.isNewUser() && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAuthToken(), that.getAuthToken());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getAuthToken(), isNewUser());
//    }
//
//    @Override
//    public String toString() {
//        return "LoginResponse{" +
//                "username='" + username + '\'' +
//                ", authToken='" + authToken + '\'' +
//                ", isNewUser=" + isNewUser +
//                '}';
//    }
}
