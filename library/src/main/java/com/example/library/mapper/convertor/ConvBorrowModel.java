package com.example.library.mapper.convertor;

import com.example.library.model.Borrow.Borrow;
import com.example.library.model.Borrow.BorrowInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConvBorrowModel {
    ConvBorrowModel instance = Mappers.getMapper(ConvBorrowModel.class);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "bookId", target = "bookId")
    Borrow BorrowInfoToBorrow(BorrowInfo borrowInfo);
}
