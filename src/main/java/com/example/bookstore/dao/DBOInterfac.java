package com.example.bookstore.dao;

import com.example.bookstore.model.Book;

import java.util.List;

public interface DBOInterfac {
    boolean addNewBook(Book b);
    boolean addNewBookWithInfo(int ISBN, String title, Integer noCopies, Integer threshold,
                                      String category, int publisher_id, String publisher_address, String publisher_name,
                                      String publisher_phoneNumber, String publicationYear, float price,
                                      List<Integer> author_id, List<String> author_Name);

}
