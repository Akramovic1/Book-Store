package com.example.bookstore.dao;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.User;

import java.util.List;

public interface DBOInterfac {
    boolean addNewBook(Book b);
    boolean addNewBookWithInfo(int ISBN, String title, Integer noCopies, Integer threshold,
                                      String category, int publisher_id, String publisher_address, String publisher_name,
                                      String publisher_phoneNumber, String publicationYear, float price,
                                      List<Integer> author_id, List<String> author_Name);
    boolean addNewUser(User user);
    boolean addNewUserWithInfo(String userName, String password, String shippingAddress, String lastName,
                                     String firstName, String email, boolean privilege,String phone);


}
