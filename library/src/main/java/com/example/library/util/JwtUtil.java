package com.example.library.util;

import com.example.library.model.User;
import com.example.library.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.security}")
    private String securityKey;

    @Autowired
    private UserService userService;

    public String generateJwtToken(User user) {
        Date now = new Date();
        Date expired = new Date(now.getTime() + 10 * 60 * 1000);

        return Jwts.builder()
            .setSubject(user.getUserName())
            .setHeader(createHeader())
            .setClaims(createBody(user))
            .setExpiration(expired)
            .signWith(SignatureAlgorithm.HS256, securityKey).compact();
    }

    /*
    (1) JWT token 이 valid 한지 확인
    (2) token 에 저장되어 있는 User 정보가 실제로 db에 있는지 확인.
     */
    public User getUserToJwtToken(String jwtToken){
        Claims body = getTokenBody(jwtToken);
        String iss = body.get("iss", String.class);

        if("server_cho".compareTo(iss) != 0)
            return null;
        User user = new User();
        user.setUserId(body.get("userId", Long.class));
        user.setUserName(body.get("userName", String.class));
        //user.setUserRoleName(body.get("userRole", String.class));

        /*
        db 에서 user 가 실제로 있는지 긁어온다.
        만약에 있다면, 이 단계에서 유저의 ROLE_NAME 도 긁어오게 된다.
         */
        user = userService.getUser(user);
        if(user != null)
            user.setUserPw(null);
        return user;
    }

    private Map<String, Object> createBody(User user) {
        Map <String, Object> body = new HashMap<>();
        body.put("userId", user.getUserId());
        body.put("userName", user.getUserName());
        //body.put("userRole", user.getUserRoleName());
        body.put("iss", "server_cho");
        return body;
    }

    private Map<String, Object> createHeader() {
        Map <String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private Claims getTokenBody(String jwtToken) {
        return Jwts
            .parser()
            .setSigningKey(securityKey)
            .parseClaimsJws(jwtToken) //jwt with sign
            .getBody();
    }

}
