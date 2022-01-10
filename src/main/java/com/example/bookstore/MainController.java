package com.example.bookstore;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class MainController implements Initializable {
    private double xoffset;

    private double yoffset;

    @FXML
    private Button profile_btn;

    @FXML
    private Button cart_btn;

    @FXML
    private TextField search;

    @FXML
    private Button logout_btn;

    @FXML
    private Button search_btn;

    @FXML
    private Button science;

    @FXML
    private Button art;

    @FXML
    private Button religion;

    @FXML
    private Button history;

    @FXML
    private Button geography;

    @FXML
    private Button addBook_btn;

    @FXML
    private Button removeBook_btn;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private ScrollPane cardLayout;

    @FXML
    private GridPane bookContainer;

    @FXML
    private Button top5;

    @FXML
    private Button totalsales;

    @FXML
    private Button top10books;

    private List<Book> booksList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        booksList = new ArrayList<>(books());
        int col = 1;
        int row = 1;
        System.out.println("Enter0");

        try{
            for(Book book : booksList){
                System.out.println("Enter1");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("bookSmall.fxml"));
                VBox box = fxmlLoader.load();
                BookSmallController bookController = fxmlLoader.getController();
                bookController.setData(book);
                if (col == 6){
                    col = 1;
                    row++;
                }
                bookContainer.add(box, col++, row);
                GridPane.setMargin(box, new Insets(20));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private List<Book> books(){
        List<Book> books = new ArrayList<>();
        Book book = new Book();
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
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        books.add(book);
        return books;
    }
    @FXML
    void openCartBtn(MouseEvent event) {
        try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 646, 400);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openProfileBtn(MouseEvent event) {
        try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 543, 542);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signoutBtnClicked(MouseEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signin.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void bookTotalSalesButtonClicked(MouseEvent event) {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            String path="totalSalesPrevMonth.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connection);
            JasperViewer.viewReport(jp,false);
            connection.close();
        }
        catch (Exception e){}
    }

    @FXML
    void top5Customers(MouseEvent event) {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            String path="top5Customers.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connection);
            JasperViewer.viewReport(jp,false);
            connection.close();
        }
        catch (Exception e){}
    }
    @FXML
    void top10SellingBooks(MouseEvent event) {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            String path="top10SellingsBooks.jrxml";
            JasperReport jr = JasperCompileManager.compileReport(path);
            JasperPrint jp = JasperFillManager.fillReport(jr,null,connection);
            JasperViewer.viewReport(jp,false);
            connection.close();
        }
        catch (Exception e){}
    }

}
