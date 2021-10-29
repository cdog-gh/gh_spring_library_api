package com.example.library.model.Borrow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "borrow 모델")
public class Borrow {
    @ApiModelProperty(value = "borrow id")
    private Long borrowId;

    @ApiModelProperty(value = "요청을 한 user 의 id")
    private Long userId;

    @ApiModelProperty(value = "borrow time")
    private Date borrowTime;

    @ApiModelProperty(value = "빌린 책 id")
    private Long bookId;
    public Long getBorrowId() {
        return borrowId;
    }
    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Date getBorrowTime() {
        return borrowTime;
    }
    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}