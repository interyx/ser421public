package com.example.graphqlserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByISBN(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    public Book save(String isbn, String title, int authorId) {
        Book result = new Book(isbn, title, authorId);
        bookRepository.save(result);
        return result;
    }

    public List<Book> getBooksByAuthorId(int id) {
        ArrayList<Book> result = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if (book.getAuthorNum() == id) {
                result.add(book);
            }
        }
        return result;
    }
    // public static ArrayList<Book> getBooksByAuthorId(int id) {
    // ArrayList<Book> bookList = new ArrayList<>();
    // for (Book book : dummyBooks) {
    // if (book.getAuthorId() == id) {
    // bookList.add(book);
    // }
    // }
    // return bookList;
    // }
}
