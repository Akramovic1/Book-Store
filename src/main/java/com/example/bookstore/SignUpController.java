package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
}
