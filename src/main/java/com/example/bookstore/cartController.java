package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class cartController {

    @FXML
    private ScrollPane cartPane;

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

}
