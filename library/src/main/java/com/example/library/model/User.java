package com.example.library.model;

public class User {
    private Long userId;
    private String userEmail;
    private String userName;
    private String userPw;
    private String userRoleName;
    private Boolean userAuth;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Boolean getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(Boolean userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userRoleName='" + userRoleName + '\'' +
                ", userAuth=" + userAuth +
                '}';
    }
}