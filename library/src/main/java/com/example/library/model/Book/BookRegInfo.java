package com.example.library.model.Book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "책 등록 정보")
public class BookRegInfo {
    @ApiModelProperty(value = "책 이름", example = "C programming", required = true)
    @NotBlank(message = "책 이름은 공백일 수 없습니다.")
    private String bookName;

    @ApiModelProperty(value = "책 분류 번호", example = "5", required = true)
    @NotNull(message = "책 분류 번호를 입력해 주세요.")
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
