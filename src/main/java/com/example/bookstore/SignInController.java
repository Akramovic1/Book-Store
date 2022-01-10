package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.Node;
import javafx.scene.Parent;


public class SignInController {
    @FXML
    private Button signupInLogin_btn;

    @FXML
    private Text forget_password;

    @FXML
    private TextField loginMail;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button SignIn_btn;

    private double xoffset;

    private double yoffset;

    @FXML
    void signInClicked(MouseEvent event) {
        String email=this.loginMail.getText();
        String password=this.loginPassword.getText();
        // if exists in database without errors
        try {
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
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("failedSignIn.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
//            dialogStage.setScene(scene);
//            dialogStage.show();
//        }catch(Exception e) {
//           e.printStackTrace();
//        }
    }

    @FXML
    void signUpClicked(MouseEvent event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            root1.setOnMousePressed(event1 -> { xoffset=event1.getSceneX();yoffset=event1.getSceneY(); });
            root1.setOnMouseDragged(e->{ stage.setX(e.getScreenX()-xoffset);stage.setY(e.getScreenY()-yoffset); });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
