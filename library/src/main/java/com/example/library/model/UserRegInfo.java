package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "유저 가입 정보")
public class UserRegInfo {

    @ApiModelProperty(value = "유저 이메일", required = true)
    @NotBlank(message = "email 은 필수 입력 항목입니다.")
    @Email(message = "유효한 email 형식이 아닙니다.")
    private String userEmail;

    @ApiModelProperty(value = "유저 id", required = true)
    @NotBlank(message = "이름 (id)는 필수 입력 항목입니다.")
    private String userName;

    @ApiModelProperty(value = "유저 비밀번호", required = true)
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
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
