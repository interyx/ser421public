package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.dto.output.DeleteBookPayload;
import com.example.graphqlserver.dto.input.DeleteBookIsbnInput;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Book> books() {
        return bookRepository.getBooks();
    }

    @QueryMapping
    public Book bookByISBN(@Argument("isbn") String isbn) {
        return bookRepository.getBookByISBN(isbn);
    }

    @QueryMapping
    public List<Book> booksByAuthorId(@Argument("id") int id) {
        return BookRepository.getBooksByAuthorId(id);
    }

    @QueryMapping
    public List<Book> booksByAuthorFirstName(@Argument("firstName") String firstName) {
        return bookRepository.getBooksByAuthorFirstName(firstName);
    }

    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput input) {
        Author author = authorRepository.getAuthorById(input.authorId());
        if (author == null) {
            throw  new IllegalArgumentException("Author with ID " + input.authorId() + "does not exist");
        }
        var book = bookRepository.save(input.isbn(), input.title(), input.authorId());
        author.getBooks().add(book);
        var out = new AddBookPayload(book);
        return out;
    }

    @MutationMapping
    public DeleteBookPayload deleteBookByISBN(@Argument DeleteBookIsbnInput input) {
        System.out.println("Deleting book with ISBN " + input.isbn());
        String result = bookRepository.deleteBookByISBN(input.isbn());
        return new DeleteBookPayload(result);
    }
}
