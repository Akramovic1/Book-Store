package com.example.bookstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 748, 519);
        stage.setTitle("BOOK STORE");
        stage.setScene(scene);
        stage.show();
    }

    public static void showWindow(String newWindow, String title, int v1, int v2) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(newWindow));
            Scene scene = new Scene(fxmlLoader.load(), v1, v2);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showErrorMessage(String errorMessage){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid Operation");
        errorAlert.setContentText(errorMessage);
        errorAlert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}