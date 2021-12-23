package com.example.library.mapper.convertor;

import com.example.library.model.Book.Book;
import com.example.library.model.Book.BookRegInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConvBookModel {
    //BookRegInfo -> Book
    ConvBookModel instance = Mappers.getMapper(ConvBookModel.class);

    List<Book> BookRegInfoListToBookList(List<BookRegInfo> bookRegInfoList);

    @Mapping(source = "bookClass", target = "bookClass")
    @Mapping(source = "bookName", target = "bookName")
    Book BookRegInfoToBook(BookRegInfo bookRegInfo);
}
