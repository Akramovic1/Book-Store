package com.example.bookstore;

import com.example.bookstore.dao.DBO;
import com.example.bookstore.model.Book;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CheckOutController {
    @FXML
    private TextField Cardholder_name;
    @FXML
    private TextField Card_number;
    @FXML
    private TextField Expiry_date;
    @FXML
    private TextField CVV;
    @FXML
    private Button payBtn;
    private UserSession userSession;

    public CheckOutController() {
    }

    public void initialize() {
        this.userSession = UserSession.getSession();
    }

    @FXML
    void PayClicked(MouseEvent event) {
        if (this.validate()) {
            DBO db = new DBO();
            if (!db.payForBook(this.userSession, Integer.parseInt(this.Card_number.getText()),
                    this.Expiry_date.getText())) {
                HashMap<Book, Integer> cart = this.userSession.getCart();
                StringBuilder sb = new StringBuilder("Book : ");
                for (Map.Entry<Book, Integer> entry : cart.entrySet())
                    sb.append(entry.getKey().getTitle()).append(" || ");
                sb.delete(sb.length() - 4, sb.length());
                sb.append(" Not Available");
                userSession.setMessage(sb.toString());
                HelloApplication.showWindow("paymentFail", "Payment Fail",
                        500, 500);
            }
            else
                HelloApplication.showWindow("paymentSuccess", "Payment success",
                        500, 500);
        }
    }

    private boolean validate() {
        if (this.Card_number.getText() != null && this.Expiry_date != null) {
            return true;
        } else {
            HelloApplication.showErrorMessage("Incorrect Data");
            return false;
        }
    }
}