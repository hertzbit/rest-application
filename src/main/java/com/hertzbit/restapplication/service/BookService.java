package com.hertzbit.restapplication.service;

import com.hertzbit.restapplication.exceptions.BookWithSameISBNExistsException;
import com.hertzbit.restapplication.exceptions.NoBookFoundException;
import com.hertzbit.restapplication.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private Map<String, Book> bookMap;
    private static final Logger LOGGER = LogManager.getLogger(BookService.class);

    public BookService() {

        bookMap = new HashMap<>();

        Book bookOne = new Book("0747532745" + "2000", "J K Rowling", "0747532745",
                "Harry Potter and the Philosopher's Stone", 2000, "Fiction");
        Book bookTwo = new Book("0747581088" + "2005", "J K Rowling", "0747581088",
                "Harry Potter and the Half-Blood Prince", 2005, "Fiction");
        Book bookThree = new Book ("9353338459" + "2019", "Joseph Murphy", "9353338459",
                "The Power of your Subconscious Mind", 2019, "Self Help");
        Book bookFour = new Book("B09JL8BMK7" + "2021", "Herbert Schildt", "B09JL8BMK7",
                "Java The Complete Reference 12th Edition", 2021, "Education");
        Book bookFive = new Book("9780553380163" + 1998, "Stephen Hawking", "9780553380163",
                "A Brief History of Time", 1998, "Science");

        bookMap.put(bookOne.getBookId(), bookOne);
        bookMap.put(bookTwo.getBookId(), bookTwo);
        bookMap.put(bookThree.getBookId(), bookThree);
        bookMap.put(bookFour.getBookId(), bookFour);
        bookMap.put(bookFive.getBookId(), bookFive);
    }

    public List<Book> getAllBooks() {

        List<Book> bookList = new ArrayList<>();
        for (Map.Entry<String, Book> entry : this.bookMap.entrySet()) {
                bookList.add(entry.getValue());
        }
        if (bookList.size() == 0) {
            throw new NoBookFoundException("There are currently no books in the library");
        }
        return bookList;
    }

    public List<Book> getAllBooksByAuthorName(String authorName) {

        List<Book> bookList = new ArrayList<>();
        for (Map.Entry<String, Book> entry : this.bookMap.entrySet()) {
            if (entry.getValue().getAuthorName().equalsIgnoreCase(authorName)) {
                bookList.add(entry.getValue());
            }
        }
        if (bookList.size() == 0) {
            throw new NoBookFoundException("There are no books in the library written by : " + authorName);
        }
        return bookList;
    }

    public List<Book> getAllBooksByTitle(String title) {

        List<Book> bookList = new ArrayList<>();
        for (Map.Entry<String, Book> entry : this.bookMap.entrySet()) {
            if (entry.getValue().getBookTitle().equalsIgnoreCase(title)) {
                bookList.add(entry.getValue());
            }
        }
        if (bookList.size() == 0) {
            throw new NoBookFoundException("There are no books in the library with title : " + title);
        }
        return bookList;
    }

    public Book getAllBooksByID(String bookId) {

        if (this.bookMap.get(bookId) != null) {
            return this.bookMap.get(bookId);
        } else {
            throw new NoBookFoundException("There are no books in the library with ID : " + bookId);
        }
    }

    public Book addNewBook (Book book) {

        for (Map.Entry<String, Book> entry : this.bookMap.entrySet()) {
            if (entry.getValue().getISBN().equals(book.getISBN())) {
                throw new BookWithSameISBNExistsException ("Books with ISBN : " + book.getISBN() + " already exists");
            }
        }
        book.setBookId(book.getISBN() + "" + book.getYearPublished());
        this.bookMap.put(book.getBookId(), book);
        return book;
    }

    public Book updateExistingBook (String bookId, Book bookFromRequest) {

        if (this.bookMap.get(bookId) != null) {
            Book bookFromDB = this.bookMap.get(bookId);
            if (bookFromRequest.getBookTitle() != null && !bookFromRequest.getBookTitle().isEmpty()) {
                bookFromDB.setBookTitle(bookFromRequest.getBookTitle());
            }
            if (bookFromRequest.getGenre() != null && !bookFromRequest.getGenre().isEmpty()) {
                bookFromDB.setGenre(bookFromRequest.getGenre());
            }
            if (bookFromRequest.getAuthorName() != null && !bookFromRequest.getAuthorName().isEmpty()) {
                bookFromDB.setAuthorName(bookFromRequest.getAuthorName());
            }
            if (bookFromRequest.getYearPublished() != null) {
                bookFromDB.setYearPublished(bookFromRequest.getYearPublished());
                bookFromDB.setBookId(bookFromDB.getISBN() + "" + bookFromDB.getYearPublished());
            }
            if (bookFromRequest.getISBN() != null && !bookFromRequest.getISBN().isEmpty()) {
                bookFromDB.setISBN(bookFromRequest.getISBN());
                bookFromDB.setBookId(bookFromDB.getISBN() + "" + bookFromDB.getYearPublished());
            }
            bookId = bookFromDB.getISBN() + "" + bookFromDB.getYearPublished();
            this.bookMap.put(bookId, bookFromDB);
            return bookFromDB;
        } else {
            throw new NoBookFoundException("There are no books in the library with ID : " + bookId);
        }
    }

    public void deleteExistingBook (String bookId) {

        if (this.bookMap.get(bookId) != null) {
            this.bookMap.remove(bookId);
        } else {
            throw new NoBookFoundException("There are no books in the library with ID : " + bookId);
        }
    }
}
