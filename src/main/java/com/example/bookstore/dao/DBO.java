package com.example.bookstore.dao;

import com.example.bookstore.UserSession;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;
import com.example.bookstore.model.User;

import java.sql.*;
import java.util.*;

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
        selecting s =new selecting();
        if (isCompleteBook(b)) {
            String sql = "insert into book (title,publisher_id,publication_year,selling_price,category,num_of_copies,threshold)values(\""
                    + b.getTitle() + "\",\"" + b.getPublisher() + "\"," + b.getPublication_year() + ",\"" + b.getPrice() +
                    "\",\"" + b.getCatagory() + "\",\"" + b.getNoCopies() + "\",\"" + b.getThreshold() + "\")";
            if (executeUpdate(sql)) {
                List<Book> books=s.getBooksByTitle(b.getTitle());
                if (books!=null) {
                    b = books.get(0);
                    return insertAuthorsOfBook(b.getAuthors(), b.getISBN());
                }
                else {
                    return false;
                }
            }
            else return false;
        }
        else return false;
    }

    public Author insertNewAuthor(String author_Name){
        selecting s =new selecting();
        String sql = "insert into authors (author_name)values(\"" + author_Name +  "\")";
        if (executeUpdate(sql)) {
            return (s.getAuthorByName(author_Name)).get(0);
        }
        else return null;
    }

    public Publisher insertNewPublisher(String publisher_name,String publisher_address,String publisher_phone){
        selecting s =new selecting();
        String sql = "insert into publisher (publisher_name,publisher_address,publisher_phone)values(\""
                + publisher_name + "\",\"" + publisher_address + "\"," + publisher_phone + "\")";
        if (executeUpdate(sql)) {
            return (s.getPublisherByName(publisher_name)).get(0);
        }
        else return null;
    }
    public boolean addNewBookWithInfo( String title, Integer noCopies, Integer threshold,
                                      String category,Publisher publisher, String publicationYear, float price,
                                      List<Author> authors){
            Book book=new Book(0, title, noCopies, threshold,
                    category, publisher, publicationYear, price, authors);
            return addNewBook(book);
    }

    public boolean addNewBookWithInfo( String title, Integer noCopies, Integer threshold,
                                       String category, String publisher_address, String publisher_name,
                                       String publisher_phoneNumber, String publicationYear, float price,
                                       List<String> author_Name){
        selecting s=new selecting();
        List<Author> bookAuthors=new ArrayList<>();
        for (int i = 0; i < author_Name.size(); i++) {
            List<Author> authors=s.getAuthorByName(author_Name.get(i));
            if (authors.size()==0){
                bookAuthors.add(insertNewAuthor(author_Name.get(i)));
            }
            else {
                bookAuthors.addAll(authors);
            }
        }
        Publisher publisher=insertNewPublisher(publisher_name,publisher_address,publisher_phoneNumber);
        return addNewBookWithInfo(title,noCopies,threshold,category,publisher,publicationYear,price,bookAuthors);

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

    @Override
    public boolean confirmOrder(int orderID) {
        String query = "delete from book_order where order_id = ?";
        try {
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, orderID);
            pStatement.execute();
            pStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean payForBook(UserSession userSession, int cardNo, String ExpiryDate) {
        int userId = userSession.getUser().getUser_id();
        addCreditCard(userId, cardNo, ExpiryDate);
        HashMap<Book, Integer> cart = userSession.getCart();
        updating u = new updating();
        HashSet<Book> temp = new HashSet<>();
//        cart.forEach((key, val) -> temp.add(key.getISBN()));
        for (Map.Entry<Book, Integer> entry : cart.entrySet()) {
            if (u.updateBookNumOfCopies(entry.getKey().getISBN(), entry.getValue()))
                temp.add(entry.getKey());
        }
        temp.forEach(cart::remove);
        return cart.isEmpty();
    }

    private void addCreditCard(int userId, int cardNo, String ExpiryDate) {
        String query = "insert into credit_card values(?, ?, ?)";
        try {
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, userId);
            pStatement.setInt(2, cardNo);
            pStatement.setString(3, ExpiryDate);
            pStatement.execute();
            pStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
