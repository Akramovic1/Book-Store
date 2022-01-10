package com.example.bookstore;

import com.example.bookstore.model.Author;
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

    private Book currentBook;

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
        currentBook = new Book();
        currentBook.setAuthors(book.getAuthors());
        currentBook.setCatagory(book.getCatagory());
        currentBook.setISBN(book.getISBN());
        currentBook.setTitle(book.getTitle());
        currentBook.setPrice(book.getPrice());
        currentBook.setNoCopies(book.getNoCopies());
        currentBook.setThreshold(book.getThreshold());
        currentBook.setPublisherName(book.getPublisher());
        currentBook.setPublication_year(book.getPublication_year());

        book_name.setText(book.getTitle());
        StringBuilder sb = new StringBuilder();
        for (Author author: book.getAuthors())
            sb.append(author.author_Name).append(" , ");
        if (!sb.isEmpty())
            book_author.setText(sb.delete(sb.length()-3, sb.length()).toString());

    }
    public void Show(MouseEvent event) {
//        UserSession userSession = UserSession.getSession();
//        userSession.setBook(currentBook);
//        HelloApplication.showWindow("bookBig.fxml", "Book-Stroe", 500,500);
        try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookItem.fxml"));
            UserSession.getSession().setBook(currentBook);
            Scene scene = new Scene(fxmlLoader.load(), 425, 335);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
