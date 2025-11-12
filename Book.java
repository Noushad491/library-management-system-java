package com.example.library;

public class Book {
    public Integer id;
    public String title;
    public String author;
    public boolean issued;

    public Book(Integer id, String title, String author, boolean issued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = issued;
    }
}
