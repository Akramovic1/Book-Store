package com.example.bookstore;

import com.example.bookstore.dao.DBO;
import com.example.bookstore.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class promoteController {
    @FXML
    private Button promote_btn;

    @FXML
    private TextField cutmoerId;

    public void promoteUser(){
        DBO dbo = new DBO();
        User user= dbo.getUserByID(Integer.parseInt(cutmoerId.getText()));
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
