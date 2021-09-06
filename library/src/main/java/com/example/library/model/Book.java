package com.example.library.model;

public class Book {
    private Long bookId;
    private String bookName;
    private Integer bookClass;
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
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
