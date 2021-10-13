package com.example.library.exception;

public class usernameDuplicate extends RuntimeException{
    private final String fieldName;
    private final String message;
    public usernameDuplicate(String fieldName, String userName){
        this.fieldName = fieldName;
        this.message = "user_name '" + userName + "'이 중복됩니다. 다른 id를 사용해 주세요.";
    }
    public String getFieldName(){
        return fieldName;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
