package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
   
}
