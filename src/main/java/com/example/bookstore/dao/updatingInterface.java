package com.example.bookstore.dao;

import com.example.bookstore.model.Book;

import java.util.List;

public interface updatingInterface {
    boolean updateBookNumOfCopies(int isbn,int sub_num_of_copies);
    boolean updateUserName(int user_id,String user_name);
    boolean updateUserFirstName(int user_id,String first_name);
    boolean updateUserLastName(int user_id,String last_name);
    boolean updateUserEmail(int user_id,String email);
    boolean updateUserPassword(int user_id,String password);
    boolean updateUserShipping_address(int user_id,String shipping_address);
    boolean updateUserPhone(int user_id,String phone);
    boolean updateUserPrivilege(int user_id,boolean privilege);



}
