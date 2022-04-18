package com.cryptoportfolio.models.responses;

import java.util.Objects;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String username;

//    public RegisterResponse(RegisterResponse.Builder builder) {
//        this.username = builder.username;
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
//    public static Builder builder() {return new Builder();}
//
//    public static final class Builder {
//        private String username;
//
//        public Builder withUsername(String username) {
//            this.username = username;
//            return this;
//        }
//
//        public RegisterResponse build() {return new RegisterResponse(this);}
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RegisterResponse that = (RegisterResponse) o;
//        return Objects.equals(getUsername(), that.getUsername());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername());
//    }
//
//    @Override
//    public String toString() {
//        return "RegisterResponse{" +
//                "username='" + username + '\'' +
//                '}';
//    }
}
