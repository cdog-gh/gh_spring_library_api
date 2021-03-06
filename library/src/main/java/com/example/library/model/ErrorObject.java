package com.example.library.model;

public class ErrorObject {
    private final String fieldName;
    private final String message;

    public ErrorObject(String fieldName, String message){
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
