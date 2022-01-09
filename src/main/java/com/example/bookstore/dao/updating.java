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

    public boolean updateUserName(int user_id,String user_name){
        try {
            String sql="update user_info set user_name = "+user_name+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserFirstName(int user_id,String first_name){
        try {
            String sql="update user_info set first_name = "+first_name+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserLastName(int user_id,String last_name){
        try {
            String sql="update user_info set last_name = "+last_name+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserEmail(int user_id,String email){
        try {
            String sql="update user_info set email = "+email+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserShipping_address(int user_id,String shipping_address){
        try {
            String sql="update user_info set shipping_address = "+shipping_address+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPhone(int user_id,String phone){
        try {
            String sql="update user_info set phone = "+phone+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPassword(int user_id,String password){
        try {
            String sql="update user_info set password = "+password+" where user_id = "+user_id;
            statement.executeUpdate(sql);
            return true;
        }
        catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPrivilege(int user_id,boolean privilege){
        try {
            String sql="update user_info set privilege = "+privilege+" where user_id = "+user_id;
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
