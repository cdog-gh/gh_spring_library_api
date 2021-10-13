package com.example.library.controller;

import com.example.library.exception.usernameDuplicate;
import com.example.library.model.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@ControllerAdvice
public class ExceptionController {
    /*
    user 를 register 할 때 user_name 이 중복된다면 떨어진다.
     */
    @ExceptionHandler({usernameDuplicate.class})
    public ResponseEntity usernameDuplicateHandler(usernameDuplicate e) {
        // log exception
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorObject(e.getFieldName(), e.getMessage()));
    }

    /*
    json input 값이 @valid 를 통과하지 못하면 MethodArgumentNotValid 예외가 떨어진다.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity badRequestHandler(MethodArgumentNotValidException e){
        List <ErrorObject> errors = new ArrayList<>();
        for(FieldError error : e.getBindingResult().getFieldErrors())
            errors.add(new ErrorObject(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
}
