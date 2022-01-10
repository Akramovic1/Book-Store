package com.example.bookstore;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class BookBigController {

    @FXML
    private VBox vboxBookContainer;

    @FXML
    private ImageView bookImg;

    @FXML
    private Label bookName;

    @FXML
    private Label bookAuthor;

    @FXML
    private Label bookPublisher;

    @FXML
    private Label publicationYear;

    @FXML
    private Label price;

    @FXML
    private Label cateogry;

    @FXML
    private Button addCart_btn;

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
