package com.example.library.service;
import com.example.library.exception.bookNotAvailable;
import com.example.library.mapper.db.BookMapper;
import com.example.library.model.Book.Book;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private SqlSessionTemplate sqlSession;
    public List<Book> selectAll(Book record, Boolean borrowFlag) {
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        return bookMapper.selectAll(record, borrowFlag);
    }
    public int addBook(Book b) {
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        return bookMapper.insert(b);
    }

    public int addBooks(List<Book> books) {
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        return bookMapper.insertListOfBook(books);
    }

    public int delBook(Long bookId) {
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        int deletedRows = bookMapper.deleteByPrimaryKey(bookId);
        if(deletedRows == 0)
            throw new bookNotAvailable(bookId);
        return bookMapper.deleteByPrimaryKey(bookId);
    }
}
