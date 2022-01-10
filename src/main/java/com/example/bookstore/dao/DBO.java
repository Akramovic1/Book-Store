package com.example.bookstore.dao;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBO implements DBOInterfac{
    private Connection connection;
    private Statement statement;
    public DBO(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            statement = connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private boolean isCompleteBook(Book b){
        return b.getISBN() != null && b.getTitle() != null && b.getPublisher() != null && b.getPublication_year() != null && b.getPrice() != null
                && b.getCatagory() != null && b.getNoCopies() != null && b.getThreshold() != null && b.getAuthors() != null && b.getAuthors().size() != 0;
    }
    private boolean insertAuthorsOfBook(List<Author> authors, int isbn){
        String sql;
        for (Author author : authors) {
            sql = "insert into book_authors(isbn,author_id) values(\"" + isbn + "\",\"" + author.getAuthor_id() + "\")";
            if (!executeUpdate(sql)) {
                return false;
            }
        }
        return true;
    }

    public boolean executeUpdate(String sql){
        try {
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                System.out.println("successfully inserted");
            } else {
                System.out.println("unsuccessful insertion ");
                return false;
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addNewBook(Book b){
        if (isCompleteBook(b)) {
            String sql = "insert into book (title,publisher_id,publication_year,selling_price,category,num_of_copies,threshold)values(\""
                    + b.getTitle() + "\",\"" + b.getPublisher() + "\"," + b.getPublication_year() + ",\"" + b.getPrice() +
                    "\",\"" + b.getCatagory() + "\",\"" + b.getNoCopies() + "\",\"" + b.getThreshold() + "\")";
            if (executeUpdate(sql)) {
                return insertAuthorsOfBook(b.getAuthors(), b.getISBN());
            }
            else return false;
        }
        else return false;
    }

    public boolean addNewBookWithInfo(int ISBN, String title, Integer noCopies, Integer threshold,
                                      String category,int publisher_id, String publisher_address, String publisher_name,
                                      String publisher_phoneNumber, String publicationYear, float price,
                                      List<Integer> author_id, List<String> author_Name){

        if (author_id.size()!=author_Name.size()){
            return false;
        }
        else {
            Publisher publisher=new Publisher(publisher_id, publisher_address, publisher_name, publisher_phoneNumber);
            List<Author> authors=new ArrayList<>();
            for (int i = 0; i < author_id.size(); i++) {
                Author author=new Author(author_id.get(i), author_Name.get(i));
                authors.add(author);
            }
            Book book=new Book(ISBN, title, noCopies, threshold,
                    category, publisher, publicationYear, price, authors);
            return addNewBook(book);
        }
    }

    private boolean isCompleteUser(User user){
        return user.getUser_name()!=null&&user.getPassword()!=null&&user.getLast_name()!=null&&user.getFirst_name()!=null&&
                user.getEmail()!=null&&user.getShipping_address()!=null&&user.getUser_id()!=null&&user.getTotal_purchases()!=null
                &&user.getPhone()!=null;
    }

    public boolean addNewUser(User user){
        if (isCompleteUser(user)) {
            String sql = "insert into user_info (user_id,user_name,password,first_name,last_name,email,phone,Shipping_address,privilege)values(\""
                    + user.getUser_id() + "\",\"" + user.getUser_name() + "\",\"" + user.getPassword() + "\"," + user.getFirst_name() + ",\"" + user.getLast_name() +
                    "\",\"" + user.getEmail() + "\",\"" + user.getPhone() + "\",\"" + user.getShipping_address() +"\",\""+user.isManager()+ "\")";
            return executeUpdate(sql);
        }
        else return false;
    }

    public boolean addNewUserWithInfo(String userName, String password, String shippingAddress, String lastName,
                                      String firstName, String email, boolean privilege,String phone){
        User user=new User(userName,password, shippingAddress, lastName, firstName, email,privilege,phone);
        return addNewUser(user);

    }

    public void insertRecordsInPUBLISHER() {
        for (int i  = 1 ; i < 10 ; i++) {
            String sql = "insert into PUBLISHER (publisher_id,publisher_name,publisher_address,publisher_phone)values("+i+",\""+("PUBLISHER "+Integer.toString(i))+"\",\""+(Integer.toString(i)+" Alexandria")+"\","+i+")";
            try {
                int result=statement.executeUpdate(sql);
                if (result > 0)
                    System.out.println("successfully inserted");

                else
                    System.out.println(
                            "unsuccessful insertion ");
            } catch (SQLException throwables) {
                //throwables.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        DBO dbo=new DBO();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
        Statement statement = connection.createStatement();

        dbo.insertRecordsInPUBLISHER();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.publisher");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("publisher_name") + resultSet.getString("publisher_address"));
        }
        //Book b= new Book(10,"harrypotter",5,2,"Art",);
    }

}
