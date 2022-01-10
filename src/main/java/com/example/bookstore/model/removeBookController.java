package com.example.bookstore.model;

import com.example.bookstore.UserSession;
import com.example.bookstore.dao.DBO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class removeBookController {
    @FXML
    private Button remove_btn;

    @FXML
    private TextField isbn;

    public void promoteUser(){
        DBO dbo = new DBO();
        User user= dbo.getUserByID(Integer.parseInt(isbn.getText()));
        User u = dbo.makeManager(user);
        if (u!=null){
            UserSession.getSession().setUser(u);
            /*top5.setVisible(user.isManager());
            totalsales.setVisible(user.isManager());
            top10books.setVisible(user.isManager());
            PromoteToManager.setVisible(!user.isManager());*/
        }
    }
}
