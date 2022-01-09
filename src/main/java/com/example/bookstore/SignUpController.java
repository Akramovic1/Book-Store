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
import java.util.regex.Pattern;

public class SignUpController {
    @FXML
    private Pane email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField emailText;

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

    private boolean correctEmailAddress(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{3}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean correctPassword(String password){
        return password.length()>=8;
    }

    @FXML
    private void signUp() {
        String password=this.password.getText();
        String firstName=this.firstName.getText();
        String lastName=this.lastName.getText();
        String username=this.username.getText();
        String phone=this.phone.getText();
        String email=this.emailText.getText();
        String address=this.address.getText();
        if (correctEmailAddress(email)&&correctPassword(password)){

        }
        else {
            //show wrong message
            //stay in the same pane.
        }

    }
}
