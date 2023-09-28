package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByAuthorNum(int authorNum);
}
