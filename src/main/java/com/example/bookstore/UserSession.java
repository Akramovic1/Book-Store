//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSession {
    private static UserSession session;
    private User user=new User();
    private Book book=new Book();
    private ArrayList<Book> books=new ArrayList<>();
    private HashMap<Book, Integer> cart=new HashMap<>();
    private String message="";

    private UserSession() {
        user=new User();
        book=new Book();
        books=new ArrayList<>();
        cart=new HashMap<>();
        message="";
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public HashMap<Book, Integer> getCart() {
        return this.cart;
    }

    public void setCart(HashMap<Book, Integer> cart) {
        this.cart = cart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static UserSession getSession() {
        if (session == null) {
            session = new UserSession();
        }
        return session;
    }
}
