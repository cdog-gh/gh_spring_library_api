package com.example.library.controller;
import com.example.library.model.Book;
import com.example.library.model.BookRegInfo;
import com.example.library.model.BookRegInfoList;
import com.example.library.service.BookService;

import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookservice;

    @RequestMapping(value="/book", method = RequestMethod.GET)
    @ApiOperation(value = "책 조회", notes = "View the book to the library",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    public ResponseEntity<List<Book>> viewBook(
        @ApiParam(value = "책 분류 번호")
        @RequestParam(required = false) Integer bookClass,
        @ApiParam(value = "책 이름")
        @RequestParam(required = false) String bookName,
        @ApiParam(value = "빌려간 책인지에 대한 여부")
        @RequestParam(required = false) Boolean borrowFlag
    ){
        Book record = new Book();
        record.setBookClass(bookClass);
        record.setBookName(bookName);
        return new ResponseEntity<>(bookservice.selectAll(record, borrowFlag), HttpStatus.OK);
    }

    @RequestMapping(value="/book", method = RequestMethod.POST)
    @ApiOperation(
        value = "책 추가", notes = "Add the book to the library",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Book> addBook(
            @ApiParam(value = "추가할 책 정보")
            @RequestBody @Valid BookRegInfo bookRegInfo){
        Book book = new Book();
        book.setBookName(bookRegInfo.getBookName());
        book.setBookClass(bookRegInfo.getBookClass());
        bookservice.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value="/books", method = RequestMethod.POST)
    @ApiOperation(
        value = "책 추가", notes = "Add the books to the library",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Book>> addBooks(
            @ApiParam(value = "추가할 책 정보들")
            @RequestBody @Valid BookRegInfoList bookInfoList){
        List<Book> books = new ArrayList<>();

        for(BookRegInfo info: bookInfoList.getRegInfoList()){
            Book record = new Book();
            record.setBookClass(info.getBookClass());
            record.setBookName(info.getBookName());
            books.add(record);
        }

        bookservice.addBooks(books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value="/book/{bookId}", method = RequestMethod.DELETE)
    @ApiOperation(
        value = "책 제거", notes = "Delete the book to the library",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delBook(
            @ApiParam(value = "제거할 책 id")
            @PathVariable("bookId") @NotNull Long bookId){
        int delCount = bookservice.delBook(bookId);
        if(delCount > 0)
            return new ResponseEntity<>(
                "id가 " + bookId + "인 책을 제거하였습니다.", HttpStatus.OK
            );
        return new ResponseEntity<>(
            "id가 " + bookId + "인 책은 빌려간 책이거나, 도서관에 없습니다.", HttpStatus.NOT_FOUND
        );
    }
}
