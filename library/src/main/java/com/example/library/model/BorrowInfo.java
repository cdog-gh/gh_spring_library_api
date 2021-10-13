package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(description = "borrow 요청 모델")
public class BorrowInfo {
    @ApiModelProperty(value = "요청을 한 user 의 id")
    @NotNull
    @Min(1)
    private Long userId;

    @ApiModelProperty(value = "빌린 책 id")
    @NotNull
    @Min(1)
    private Long bookId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
