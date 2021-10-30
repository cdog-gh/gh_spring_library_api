package com.example.library.exception;

public class returnBookNotAvailable extends RuntimeException{
    private final String message;
    public returnBookNotAvailable(Long borrowId){
        this.message = "borrow_id가 " + borrowId + "인 주문을 찾을 수 없습니다.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
