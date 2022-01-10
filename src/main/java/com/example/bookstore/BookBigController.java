package com.example.bookstore;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class BookBigController {

    @FXML
    private Pane pane;

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
    private Label cateogry;

    @FXML
    private Button addCart_btn;

    public void setData(Book book){
        Author author = new Author(0, "Ahmed");
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        book.setAuthors(authors);
        book.setCatagory("Science");
        book.setISBN(100000);
        book.setTitle("Genius");
        book.setPrice(170);
        book.setNoCopies(20);
        book.setThreshold(5);
        Publisher publisher = new Publisher(10, "Alex", "Ahmed Akram", "01149800000");
        book.setPublisherName(publisher);
        book.setPublication_year("2020");
    }
}
