package com.example.bookstore;

import com.example.bookstore.dao.DBO;
import com.example.bookstore.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cartController {

    @FXML
    private ListView<String> cartList;

    @FXML
    private Button addBookCart_btn;

    @FXML
    private Button removeBookCart_btn;

    @FXML
    private Label totalPrice;

    @FXML
    void openCheckoutBtn(MouseEvent event) {
        try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckOut.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 750, 520);
            dialogStage.setScene(scene);
            dialogStage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void initialize(){
        HashMap<Book,Integer> cart = UserSession.getSession().getCart();
        float totalSum=0;
        for (Map.Entry<Book, Integer> set :cart.entrySet()) {
            cartList.getItems().add(set.getKey().getISBN()+","+set.getKey().getTitle()+","+set.getKey().getPrice()+","+set.getKey().getPublisher());
            totalSum+=set.getKey().getPrice();
        }
        totalPrice.setText(String.valueOf(totalSum));

    }

    public void remove() {
        String value=cartList.getSelectionModel().getSelectedItem();
        if (value!=null){
            String[] values=value.split(",");

        }
    }
}
