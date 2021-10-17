package com.example.library.filter;

import com.example.library.exception.jwtTokenNotAvailable;
import com.example.library.model.ErrorObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//https://stackoverflow.com/questions/34595605/how-to-manage-exceptions-thrown-in-filters-in-spring/55864206
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            //JwtFilter 를 호출하는데, 이 필터에서 jwtTokenNotAvailable 이 떨어진다.
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch(jwtTokenNotAvailable e){
            //https://stackoverflow.com/questions/57194249/how-to-return-response-as-json-from-spring-filter
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setCharacterEncoding("UTF-8");
            ErrorObject errorObject = new ErrorObject("jwt_access_token", e.getMessage());
            objectMapper.writeValue(httpServletResponse.getWriter(), errorObject);
        }
    }
}
