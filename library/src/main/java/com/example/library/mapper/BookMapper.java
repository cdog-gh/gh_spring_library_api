package com.example.library.mapper;

import com.example.library.model.Book.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Long bookId);
    int insert(Book record);
    Book selectByPrimaryKey(Long bookId);
    List<Book> selectAll(@Param("book")Book record,
                         @Param("borrowFlag")Boolean borrowFlag);
    int updateByPrimaryKey(Book record);
    int insertListOfBook(List<Book> books);
}
