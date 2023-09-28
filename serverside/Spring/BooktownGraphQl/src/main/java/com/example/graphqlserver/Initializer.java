package com.example.graphqlserver;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;

@Configuration
public class Initializer {
    @Bean CommandLineRunner bookCommandLineRunner(BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {
            List<Book> books = Arrays.asList(
                new Book("123456789", "The Road Not Taken", 0),
                new Book("987654321", "To Kill a Mockingbird", 1),
                new Book("456789123", "The Great Gatsby", 2)
            );
            bookRepository.saveAll(books);
            List<Author> authors = Arrays.asList(
                new Author(0, "Robert", "Frost", bookRepository.findByAuthorNum(0)),
                new Author(1, "Martin", "Fowler", bookRepository.findByAuthorNum(1)),
                new Author(2, "Kevin", "Gary", bookRepository.findByAuthorNum(2))
            );
            authorRepository.saveAll(authors);
        };
    }
}
