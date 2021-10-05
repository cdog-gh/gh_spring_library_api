package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "유저 로그인 정보")
public class UserLoginInfo {

    @NotBlank(message = "이름 (id)는 필수 입력 항목입니다.")
    @ApiModelProperty(value = "유저 id", required = true)
    private String userName;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @ApiModelProperty(value = "유저 비밀번호", required = true)
    private String userPw;

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
