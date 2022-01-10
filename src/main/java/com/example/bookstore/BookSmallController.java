package com.example.bookstore;

import com.example.bookstore.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BookSmallController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private ImageView bookImg;

    @FXML
    private Label book_name;

    @FXML
    private Label book_author;

    @FXML
    private Button showDetails_btn;

    public void setData(Book book){
        book.setAuthors(book.getAuthors());
        book.setCatagory(book.getCatagory());
        book.setISBN(book.getISBN());
        book.setTitle(book.getTitle());
        book.setPrice(book.getPrice());
        book.setNoCopies(book.getNoCopies());
        book.setThreshold(book.getThreshold());
        book.setPublisherName(book.getPublisher());
        book.setPublication_year(book.getPublication_year());
    }
}
