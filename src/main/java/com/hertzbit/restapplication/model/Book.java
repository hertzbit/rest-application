package com.hertzbit.restapplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book implements Serializable  {

    private String bookId;
    private String authorName;
    private String ISBN;
    private String bookTitle;
    private Integer yearPublished;
    private String genre;

    public Book() {};

    public Book(String bookId, String authorName, String ISBN, String bookTitle, Integer yearPublished, String genre) {
        this.bookId = bookId;
        this.authorName = authorName;
        this.ISBN = ISBN;
        this.bookTitle = bookTitle;
        this.yearPublished = yearPublished;
        this.genre = genre;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", authorName='" + authorName + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", yearPublished=" + yearPublished +
                ", genre='" + genre + '\'' +
                '}';
    }
}
