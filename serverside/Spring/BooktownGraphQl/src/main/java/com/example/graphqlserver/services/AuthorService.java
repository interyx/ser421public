package com.example.graphqlserver.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;


@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author save(String firstName, String lastName) {
        List<Book> book = new ArrayList<>();
        int id = (int)authorRepository.count();
        Author newAuthor = new Author(id, firstName, lastName, book);
        authorRepository.save(newAuthor);
        return newAuthor;
    }
    //  public List<Author> getAuthors() {
    //     return dummyAuthors;
    // }

    // public Author getAuthorById(int id) {
    //     for (Author author : dummyAuthors) {
    //         if (author.getId() == id) {
    //             return author;
    //         }
    //     }
    //     return null;
    // }

    // public Author save(String firstName, String lastName) {
    //     List<Book> book = new ArrayList<>();
    //     int nextId = dummyAuthors.isEmpty() ? 0 : dummyAuthors.get(dummyAuthors.size() - 1).getId() + 1;
    //     Author newAuthor = new Author(nextId, firstName, lastName, book);
    //     dummyAuthors.add(newAuthor);
    //     return newAuthor;
    // }
}
