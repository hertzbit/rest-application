package com.hertzbit.restapplication.controller;

import com.hertzbit.restapplication.model.Book;
import com.hertzbit.restapplication.service.BookService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping ("/api/books")
public class BookController {

    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping (produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks (@RequestParam(value = "delay", defaultValue = "0") String delay,
                                                   @RequestParam(value = "authorName", defaultValue = "") String authorName,
                                                   @RequestParam(value = "title", defaultValue = "") String title,
                                                   @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                                   @RequestParam(value = "sortingOrder", defaultValue = "asc") String sortingOrder) {

        try {
            Thread.sleep(Long.valueOf(delay));
        } catch (InterruptedException exception) {
            LOGGER.error(exception.getMessage());
        }
        List<Book> bookList = new ArrayList<>();
        if (!authorName.equals("")) {
            bookList = this.bookService.getAllBooksByAuthorName(authorName);
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }

        if (!title.equals("")) {
            bookList = this.bookService.getAllBooksByTitle(title);
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }

        if (!sortBy.equals("")) {
            bookList = this.bookService.getAllBooks();
            if (sortingOrder.equals("asc")) {
                Collections.sort(bookList, new Comparator<Book>() {
                    @Override
                    public int compare(Book currentBook, Book anotherBook) {
                        return currentBook.getYearPublished().compareTo(anotherBook.getYearPublished());
                    }
                });
            } else if (sortingOrder.equals("desc")){
                Collections.sort(bookList, new Comparator<Book>() {
                    @Override
                    public int compare(Book currentBook, Book anotherBook) {
                        return anotherBook.getYearPublished().compareTo(currentBook.getYearPublished());
                    }
                });
            }
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }

        bookList = this.bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping (value = "/{bookId}", produces = "application/json")
    public ResponseEntity<?> getRequestedBook(@PathVariable("bookId") String bookId,
                                              @RequestParam(value = "delay", defaultValue = "0") String delay) {

        try {
            Thread.sleep(Long.valueOf(delay));
        } catch (InterruptedException exception) {
            LOGGER.error(exception.getMessage());
        }
        Book bookFromDatabase = this.bookService.getAllBooksByID(bookId);
        return new ResponseEntity<>(bookFromDatabase, HttpStatus.OK);
    }

    @PostMapping (produces = "application/json")
    @ApiResponse(
            responseCode = "201",
            description = "New Book Created",
            content = @Content(schema = @Schema(implementation = Book.class))
    )
    public ResponseEntity<?> addBookToDatabase (@RequestBody Book book) {

        Book savedBookWithBookID = this.bookService.addNewBook(book);
        return new ResponseEntity<>(savedBookWithBookID, HttpStatus.CREATED);
    }

    @PutMapping (value = "/{bookId}", produces = "application/json")
    public ResponseEntity<?> updateExistingBook (@PathVariable("bookId") String bookId, @RequestBody Book book) {

        Book updatedBook = this.bookService.updateExistingBook(bookId, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping (value = "/{bookId}")
    public ResponseEntity<?> deleteBookFromDatabase (@PathVariable("bookId") String bookId) {

        this.bookService.deleteExistingBook(bookId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
