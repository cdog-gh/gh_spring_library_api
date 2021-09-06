package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "책 등록 정보")
public class BookRegInfo {
    @ApiModelProperty(value = "책 이름", example = "C programming", required = true)
    private String bookName;

    @ApiModelProperty(value = "책 분류 번호", example = "5", required = true)
    private Integer bookClass;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookClass() {
        return bookClass;
    }

    public void setBookClass(Integer bookClass) {
        this.bookClass = bookClass;
    }
}
