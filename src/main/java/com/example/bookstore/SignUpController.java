package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {
    @FXML
    private Pane email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private Button SignUp_btn;

    @FXML
    private void signUp() {
        String pwrd=password.getText();

    }

}
