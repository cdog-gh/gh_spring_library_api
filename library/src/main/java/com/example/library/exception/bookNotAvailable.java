package com.example.library.exception;

public class bookNotAvailable extends RuntimeException{
    private final String fieldName;
    private final String message;
    public bookNotAvailable(String fieldName, Long bookId){
        this.fieldName = fieldName;
        this.message = "book_id가 " + bookId + "인 책은 없거나 누군가 빌려간 상태입니다.";
    }
    public String getFieldName(){
        return fieldName;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
