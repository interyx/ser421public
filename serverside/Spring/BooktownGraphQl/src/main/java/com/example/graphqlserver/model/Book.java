package com.example.graphqlserver.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    private String isbn;

    private String title;

    private int authorNum;

    public Book(String isbn, String title, int authorNum) {
        this.isbn = isbn;
        this.title = title;
        this.authorNum = authorNum;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorNum() {
        return authorNum;
    }

    public void setAuthorNum(int authorNum) {
        this.authorNum = authorNum;
    }
}
