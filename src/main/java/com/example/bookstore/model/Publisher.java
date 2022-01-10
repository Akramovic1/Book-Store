package com.example.bookstore.model;

/*import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;*/

public class Publisher {
    private Integer publisher_id;
    private String Address;
    private String publisher_name;
    private String phone_number;

    public Publisher(int id, String address, String name,String phoneNumber) {
        this.publisher_id = id;
        this.Address = address;
        this.publisher_name = name;
        this.phone_number = phoneNumber;
    }

    public Publisher(int id) {
        this.publisher_id = id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }
}
