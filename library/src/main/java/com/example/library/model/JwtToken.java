package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "토큰 관련 정보")
public class JwtToken {

    @ApiModelProperty(value = "access 토큰")
    private String accessToken = "";

    @ApiModelProperty(value = "refresh 토큰")
    private String refreshToken = "";

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
