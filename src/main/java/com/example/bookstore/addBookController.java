//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.bookstore;

import com.example.bookstore.dao.DBO;
import com.example.bookstore.dao.selecting;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class addBookController {

    @FXML
    private Button add_btn;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    public ComboBox<String> publishers;
    @FXML
    private TextField year;
    @FXML
    private TextField price;
    @FXML
    private TextField cateogry;

    //    private HashSet<Integer> IDs;
    private ArrayList<Author> bookAuthors;

    private Publisher currentPublisher;
    private DBO db;

    private selecting s;

    public void initialize() {
        s = new selecting();
        db = new DBO();
        this.s.getAllPublishers().forEach(p ->
                publishers.getItems().add(p.getPublisher_id() + ">>>" + p.getPublisher_name()));
//        this.IDs = new HashSet<Integer>();
//        this.bookAuthors = new ArrayList();
    }

    public void addBook() {
        if (this.validate()) {
            Book book = new Book(0, this.title.getText(),
                    1, 1, this.cateogry.getText(),
                    this.currentPublisher, this.year.getText(),
                    Float.parseFloat(this.price.getText()), bookAuthors);
            if (this.db.addNewBook(book)) {
                HelloApplication.showWindow("main.fxml", "Main", 500, 500);
            } else {
                HelloApplication.showErrorMessage("Couldn't Insert in DataBase");
            }

        }
    }

    private void getAuthors() {
        bookAuthors = new ArrayList<>();
        String[] values = author.getText().split(",");
        for (String string : values)
            bookAuthors.add(new Author(0, string));
    }

    private boolean validate() {
        if (this.publishers.getSelectionModel().getSelectedItem() != null) {
            String[] values = ((String)this.publishers.getSelectionModel().selectedItemProperty().getValue()).split(">>>");
            this.currentPublisher = new Publisher(Integer.parseInt(values[0]));
        }
        getAuthors();
//        if (publisher.getText() != null)
//            currentPublisher = new Publisher(0, null, publisher.getText(), null);

        if (this.title.getText() != null && this.currentPublisher != null &&
                this.author.getText() != null &&
                this.year.getText() != null && this.price.getText() != null &&
                this.cateogry.getText() != null && !this.bookAuthors.isEmpty()) {
            return true;
        } else {
            HelloApplication.showErrorMessage("Incorrect Data");
            return false;
        }
    }

//    public void addToAuthors() {
//        String[] values = ((String)this.Authors.getSelectionModel().selectedItemProperty().getValue()).split(">>>");
//        Author author = new Author(Integer.parseInt(values[0]), values[1]);
//        if (this.IDs.contains(author.author_id)) {
//            HelloApplication.showErrorMessage("Author already selected");
//        } else {
//            System.out.println(author.author_Name);
//            this.IDs.add(author.author_id);
//            this.bookAuthors.add(author);
//        }
//    }

    public static void main(String[] args) {
        String k = "iiii--ooo";
        String[] ff = k.split("");
        System.out.println(ff[0] + "...." + ff[1]);
    }

}
