package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    private Button signin;

    private double xoffset;

    private double yoffset;

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
    private void signUp(MouseEvent event) {
        String password=this.password.getText();
        String firstName=this.firstName.getText();
        String lastName=this.lastName.getText();
        String username=this.username.getText();
        String phone=this.phone.getText();
        String email=this.emailText.getText();
        String address=this.address.getText();
//        if (correctEmailAddress(email)&&correctPassword(password)){
//
//        }
//        else {
//            //show wrong message
//            //stay in the same pane.
//        }
        try {// if correctEmailAddress && correctPassword
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> {
                xoffset = event1.getSceneX();
                yoffset = event1.getSceneY();
            });
            root1.setOnMouseDragged(e -> {
                stage.setX(e.getScreenX() - xoffset);
                stage.setY(e.getScreenY() - yoffset);
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
        //else error
//        try {
//            Stage dialogStage = new Stage();
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("failedSignUp.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
//            dialogStage.setScene(scene);
//            dialogStage.show();
//        }catch(Exception e) {
//           e.printStackTrace();
//        }

    }
    @FXML
    void signIn(MouseEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signin.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> {
                xoffset = event1.getSceneX();
                yoffset = event1.getSceneY();
            });
            root1.setOnMouseDragged(e -> {
                stage.setX(e.getScreenX() - xoffset);
                stage.setY(e.getScreenY() - yoffset);
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
