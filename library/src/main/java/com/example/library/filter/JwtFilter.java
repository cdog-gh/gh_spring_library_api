package com.example.library.filter;

import com.example.library.model.User;
import com.example.library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("jwt_access_token");
        // token 을 얻어왔다면 인증 정보를 채운다.
        if(token != null){
            // user 정보를 채운다.
            User user = jwtUtil.getUserToJwtToken(token);

            //실제로 User 가 있는지 check!
            //User 가 존재하면, 이 정보를 토대로 인증 정보를 넘겨줄 거임.
            if(user != null) {
                Authentication authInfo = convUserToAuthInfo(user);
                SecurityContextHolder.getContext().setAuthentication(authInfo);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private Authentication convUserToAuthInfo(User user) {
        List <SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getUserRoleName()));
        return new UsernamePasswordAuthenticationToken(
            user, null, authority
        );
    }
}
