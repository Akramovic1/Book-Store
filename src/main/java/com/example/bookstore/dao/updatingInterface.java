package com.example.bookstore.dao;

import com.example.bookstore.model.Book;

import java.util.List;

public interface updatingInterface {
    boolean updateBookNumOfCopies(int isbn,int sub_num_of_copies);
}
