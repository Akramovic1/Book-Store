package com.example.bookstore;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

public class BookBigController {

    Book currentBook;

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

    /*public void setData(Book book){
        book.setAuthors(book.getAuthors());
        book.setCatagory(book.getCatagory());
        book.setISBN(book.getISBN());
        book.setTitle(book.getTitle());
        book.setPrice(book.getPrice());
        book.setNoCopies(book.getNoCopies());
        book.setThreshold(book.getThreshold());
        book.setPublisherName(book.getPublisher());
        book.setPublication_year(book.getPublication_year());
        currentBook=book;
    }*/

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

        currentBook = book;

        bookName.setText(book.getTitle());
        bookPublisher.setText(book.getPublisher().getPublisher_name());
        publicationYear.setText(book.getPublication_year());
        StringBuilder sb = new StringBuilder();
        for (Author author: book.getAuthors())
            sb.append(author.author_Name).append(" , ");
        if (!sb.isEmpty())
            bookAuthor.setText(sb.delete(sb.length()-3, sb.length()).toString());
        price.setText(book.getPrice().toString());
        cateogry.setText(book.getCatagory());
    }

    public void addToCart() {
        UserSession userSession = UserSession.getSession();
        HashMap<Book, Integer> cart = userSession.getCart();
        boolean test = false;
//
//
//        cart.forEach((k, v) -> {
//            if (Objects.equals(k.getISBN(), currentBook.getISBN())) {
//                v = v + 1;
//                test.set(true);
//            }
//        });
//        if (!test.get())
//            cart.put(currentBook, 1);

        for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
            if (Objects.equals(entry.getKey().getISBN(), currentBook.getISBN())) {
                entry.setValue(entry.getValue() + 1);
                test = true;
            }
        }
        if (test) {
            cart.put(currentBook, 1);
        }
        try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 543, 542);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        currentBook=UserSession.getSession().getBook();
        bookName.setText(currentBook.getTitle());
        bookPublisher.setText(currentBook.getPublisher().getPublisher_name());
        publicationYear.setText(currentBook.getPublication_year());
        StringBuilder sb = new StringBuilder();
        for (Author author: currentBook.getAuthors())
            sb.append(author.author_Name).append(" , ");
        if (!sb.isEmpty())
            bookAuthor.setText(sb.delete(sb.length()-3, sb.length()).toString());
        price.setText(currentBook.getPrice().toString());
        cateogry.setText(currentBook.getCatagory());
    }
}
