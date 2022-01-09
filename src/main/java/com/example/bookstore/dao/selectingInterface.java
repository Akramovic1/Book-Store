package com.example.bookstore.dao;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;

import java.util.List;
import java.util.Map;

public interface selectingInterface {
    Publisher getPublisher(int publisher_id);
    Author getAuthor(int author_id);
    List<Author> getAuthorsOfBook(int isbn);
    Book getBook(int isbn);
    List<Book> getBooksByTitle(String title);
    List<Book> getBooksByISBNAndTitle(int isbn,String title);
    //any of them can be null
    List<Book> searchBookWithISBNAndTittle(Integer isbn,String title);
    //Map of Attribute : value of that attribute
    List<Book> searchBookWithCategoryAuthorPublisherName(Map<String,String> hash);


}
