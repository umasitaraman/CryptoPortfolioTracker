package com.cryptoportfolio.models;

import java.io.Serializable;
import java.util.Objects;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements Serializable {

    private static final long serialVersionUID = 2166465282947561034L;

    private String username;
    private String password;
    private Boolean isNewUser;


//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Boolean getNewUser() {
//        return isNewUser;
//    }
//
//    public void setNewUser(Boolean newUser) {
//        isNewUser = newUser;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserModel)) return false;
//        UserModel userModel = (UserModel) o;
//        return Objects.equals(getUsername(), userModel.getUsername()) && Objects.equals(getPassword(), userModel.getPassword()) && Objects.equals(isNewUser, userModel.isNewUser);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getPassword(), isNewUser);
//    }
//
//    @Override
//    public String toString() {
//        return "UserModel{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", isNewUser=" + isNewUser +
//                '}';
//    }
}
