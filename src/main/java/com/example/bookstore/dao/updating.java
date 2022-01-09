package com.example.bookstore.dao;

import com.example.bookstore.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class updating implements updatingInterface{
    private Connection connection;
    private Statement statement;
    public updating(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            statement = connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean updateBookNumOfCopies(int isbn,int sub_num_of_copies){
        try {
            String sql="update book set num_of_copies = num_of_copies-"+sub_num_of_copies+" where isbn = "+isbn;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args)  {
        updating u = new updating();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            Statement statement = connection.createStatement();
            System.out.println("True");
            String sql="update book set num_of_copies = num_of_copies-1 where isbn = 1 ";
            statement.executeUpdate(sql);
            System.out.println("True");
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
