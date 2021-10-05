package com.example.library.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@ApiModel(description = "책 등록 정보들")
public class BookRegInfoList {

    @Valid
    @ApiModelProperty(value = "추가할 책 정보들", example = "", required = true)
    @NotNull(message = "책 등록 정보는 비어있을 수 없습니다.")
    @Size(min = 1, message = "등록할 책 정보는 하나 이상 있어야 합니다.")
    private List<BookRegInfo> regInfoList;

    public List<BookRegInfo> getRegInfoList() {
        return regInfoList;
    }

    public void setRegInfoList(List<BookRegInfo> regInfoList) {
        this.regInfoList = regInfoList;
    }
}
