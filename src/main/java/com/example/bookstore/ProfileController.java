package com.example.bookstore;

import com.example.bookstore.dao.DBO;
import com.example.bookstore.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        /*userNameLabel.setText("Rana Ayman");
        userNameF.setText("Rana");
        firstNameF.setText("Rana");
        lastNameF.setText("Ayman");
        passwordF.setText("147258369");
        emailF.setText("rana@gmail.com");
        phoneF.setText("0123789");
        addressF.setText("Address");
        */
        User user = UserSession.getSession().getUser();
        userNameLabel.setText(user.getUser_name());
        userNameF.setText(user.getUser_name());
        firstNameF.setText(user.getFirst_name());
        lastNameF.setText(user.getLast_name());
        passwordF.setText(user.getPassword());
        emailF.setText(user.getEmail());
        phoneF.setText(user.getPhone());
        addressF.setText(user.getShipping_address());

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

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
        DBO dbo = new DBO();
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

        if (correctEmailAddress(email) && correctPassword(password)){
            User user = UserSession.getSession().getUser();
            boolean changed = false;
            if (user.getUser_name()!=userName){
                dbo.updateUserUser_name(user.getUser_id(),userName);
                changed=true;
            }
            if (user.getFirst_name()!=firstName){
                dbo.updateUserFirst_name(user.getUser_id(),firstName);
                changed=true;
            }
            if (user.getLast_name()!=lastName){
                dbo.updateUserLast_name(user.getUser_id(),lastName);
                changed=true;
            }
            if (user.getPassword()!=password){
                dbo.updateUserPassword(user.getUser_id(),password);
                changed=true;
            }
            if (user.getEmail()!=email){
                dbo.updateUserEmail(user.getUser_id(),email);
                changed=true;
            }
            if (user.getPhone()!=phone){
                dbo.updateUserPhone(user.getUser_id(),phone);
                changed=true;
            }
            if (user.getShipping_address()!=address){
                dbo.updateUserShipping_address(user.getUser_id(),address);
                changed=true;
            }
            if (changed){
                UserSession.getSession().setUser(dbo.getUserByEmailAndPassword(email, password));
                init();
            }
        }
        else {
          //else error
           try {
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("failedSignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            dialogStage.setScene(scene);
            dialogStage.show();
          }catch(Exception e) {
            e.printStackTrace();
          }
        }
    }

}
