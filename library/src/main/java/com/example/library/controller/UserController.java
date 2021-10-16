package com.example.library.controller;

import com.example.library.model.JwtToken;
import com.example.library.model.User;
import com.example.library.model.UserLoginInfo;
import com.example.library.model.UserRegInfo;
import com.example.library.service.UserService;
import com.example.library.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value="/reg", method = RequestMethod.POST)
    public ResponseEntity<UserRegInfo> regUser(@RequestBody @Valid UserRegInfo regInfo){
        String newPw = new BCryptPasswordEncoder().encode(regInfo.getUserPw());

        User user = new User();
        user.setUserName(regInfo.getUserName());
        user.setUserEmail(regInfo.getUserEmail());
        user.setUserPw(newPw);
        userService.regUser(user);
        return new ResponseEntity<>(regInfo, HttpStatus.OK);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ApiOperation(value = "로그인", notes = "로그인이 성공하면 token 을 발급한다.")
    public ResponseEntity<JwtToken> login(
            @ApiParam(value = "로그인 할 유저 정보")
            @RequestBody @Valid UserLoginInfo loginInfo){
        JwtToken jwt = new JwtToken();
        User user = new User();
        user.setUserName(loginInfo.getUserName());
        user.setUserPw(loginInfo.getUserPw());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User result = userService.getUser(user);

        if((result == null) || (!encoder.matches(user.getUserPw(), result.getUserPw())))
            return new ResponseEntity<>(jwt, HttpStatus.UNAUTHORIZED);

        user.setUserPw("");
        jwt.setAccessToken(jwtUtil.generateJwtToken(result));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
