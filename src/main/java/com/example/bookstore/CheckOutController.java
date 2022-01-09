package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CheckOutController {
//Scene scene = new Scene(fxmlLoader.load(), 750, 520);
    @FXML
    private TextField Cardholder_name;

    @FXML
    private TextField Card_number;

    @FXML
    private TextField Expiry_date;

    @FXML
    private TextField CVV;

    @FXML
    private Button payBtn;

    @FXML
    void PayClicked(MouseEvent event) {

    }

}
