package com.example.bookstore.dao;

import com.example.bookstore.UserSession;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.User;

import java.util.List;

public interface DBOInterfac {
    boolean addNewBook(Book b);
    boolean addNewBookWithInfo( String title, Integer noCopies, Integer threshold,
                                String category,Publisher publisher, String publicationYear, float price,
                                List<Author> authors);
    boolean addNewBookWithInfo( String title, Integer noCopies, Integer threshold,
                        String category, String publisher_address, String publisher_name,
                        String publisher_phoneNumber, String publicationYear, float price,
                        List<String> author_Name);
    boolean addNewUser(User user);
    boolean addNewUserWithInfo(String userName, String password, String shippingAddress, String lastName,
                                     String firstName, String email, boolean privilege,String phone);

    Publisher insertNewPublisher(String publisher_name,String publisher_address,String publisher_phone);
    Author insertNewAuthor(String author_Name);

    boolean confirmOrder(int orderID);
    boolean payForBook(UserSession userSession, int cardNo, String ExpiryDate);

    User getUserByEmailAndPassword(String email,String password);
    User getUserByID(int id);
    User insertUser(String user_name, String password, String first_name, String last_name,
                    String email, String phone, String Shipping_address);

}
