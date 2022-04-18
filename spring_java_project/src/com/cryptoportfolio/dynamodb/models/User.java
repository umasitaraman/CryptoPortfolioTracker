package com.cryptoportfolio.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "users")
public class User {

    private String username;
    private String password;
    private Boolean isNewUser;

    @DynamoDBHashKey(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "is_new_user")
    public Boolean getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(Boolean newUser) {
        isNewUser = newUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getIsNewUser(), user.getIsNewUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getIsNewUser());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isNewUser=" + isNewUser +
                '}';
    }
}
