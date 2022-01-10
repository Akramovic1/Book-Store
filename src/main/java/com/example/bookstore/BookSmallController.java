package com.example.bookstore;

import com.example.bookstore.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    public void Show(MouseEvent event) {
//        UserSession userSession = UserSession.getSession();
//        userSession.setBook(currentBook);
//        HelloApplication.showWindow("bookBig.fxml", "Book-Stroe", 500,500);
        try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookBig.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
