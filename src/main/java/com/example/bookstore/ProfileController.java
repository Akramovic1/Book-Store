package com.example.bookstore;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable  {
//Scene scene = new Scene(fxmlLoader.load(), 543.0, 542.0);
    @FXML
    private Label userNameLabel;

    @FXML
    private Button editBTn;

    @FXML
    private TextField userNameF;

    @FXML
    private TextField firstNameF;

    @FXML
    private TextField lastNameF;

    @FXML
    private PasswordField passwordF;

    @FXML
    private TextField emailF;

    @FXML
    private TextField phoneF;

    @FXML
    private TextField addressF;

    @FXML
    private Button saveChangesBtn;

    private void init() {
    // userNameLabel to be set like :  userNameLabel = new Label(user.name);
    // and all "7" page fields need to be filled using database
    //dummy data
        userNameLabel.setText("Rana Ayman");
        userNameF.setText("Rana");
        firstNameF.setText("Rana");
        lastNameF.setText("Ayman");
        passwordF.setText("147258369");
        emailF.setText("rana@gmail.com");
        phoneF.setText("0123789");
        addressF.setText("Address");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    @FXML
    void EditProfileBtn(MouseEvent event) {
        userNameF.setEditable(true);
        firstNameF.setEditable(true);
        lastNameF.setEditable(true);
        passwordF.setEditable(true);
        emailF.setEditable(true);
        phoneF.setEditable(true);
        addressF.setEditable(true);
    }

    @FXML
    void saveChangesBtn(MouseEvent event) {
        userNameF.setEditable(false);
        firstNameF.setEditable(false);
        lastNameF.setEditable(false);
        passwordF.setEditable(false);
        emailF.setEditable(false);
        phoneF.setEditable(false);
        addressF.setEditable(false);
        String userName=this.userNameF.getText();
        String firstName=this.firstNameF.getText();
        String lastName=this.lastNameF.getText();
        String password=this.passwordF.getText();
        String email=this.emailF.getText();
        String phone=this.phoneF.getText();
        String address=this.addressF.getText();

        // update data in database
    }

}
