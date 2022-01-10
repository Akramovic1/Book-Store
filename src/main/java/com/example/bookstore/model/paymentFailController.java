package com.example.bookstore.model;

import com.example.bookstore.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class paymentFailController {

    @FXML
    private Label message;

    private UserSession userSession;
    public void initialize(){
        userSession = UserSession.getSession();
        message.setText(userSession.getMessage());
    }

}