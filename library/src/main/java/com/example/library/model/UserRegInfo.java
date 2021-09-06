package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "유저 가입 정보")
public class UserRegInfo {
    @ApiModelProperty(value = "유저 이메일", required = true)
    private String userEmail;

    @ApiModelProperty(value = "유저 id", required = true)
    private String userName;

    @ApiModelProperty(value = "유저 비밀번호", required = true)
    private String userPw;

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
}
