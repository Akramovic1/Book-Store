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
                    + b.getTitle() + "\",\"" + b.getPublisher().getPublisher_id() + "\"," + b.getPublication_year() + ",\"" + b.getPrice() +
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

    public User insertUser(String user_name, String password, String first_name, String last_name,
                           String email, String phone, String Shipping_address) {
        selecting s =new selecting();
        String sql = "insert into user_info (user_name,password,first_name,last_name,email,phone,Shipping_address,privilege)values(\""
                + user_name + "\",\"" + password + "\",\"" + first_name + "\",\"" + last_name +
                "\",\"" + email + "\",\"" + phone + "\",\"" + Shipping_address+ "\"," + false + ")";
        if (executeUpdate(sql)) {
            return getUserByEmailAndPassword(email, password);
        }
        else return null;

    }

    public User getUserByEmailAndPassword(String email,String password){
        try {
            String sql = "SELECT * FROM user_info WHERE email = \""+email+"\" AND password = \""+password+"\"";
            ResultSet resultSet = statement.executeQuery(sql);
            User user = null;
            int n=0;
            while (resultSet.next()&&n<1) {
                user=new User(resultSet.getString("user_name"), resultSet.getString("password"),
                        resultSet.getString("Shipping_address"), resultSet.getString("last_name"),
                        resultSet.getString("first_name"), resultSet.getString("email"),
                        resultSet.getBoolean("privilege"),resultSet.getString("phone"));
                user.setUser_id(resultSet.getInt("user_id"));
                n++;
            }
            return user;
        }
        catch (Exception e){
            return null;
        }
    }

    public User getUserByID(int id){
        try {
            String sql = "SELECT * FROM user_info WHERE user_id = \""+id+"\"";
            ResultSet resultSet = statement.executeQuery(sql);
            User user = null;
            int n=0;
            while (resultSet.next()&&n<1) {
                user=new User(resultSet.getString("user_name"), resultSet.getString("password"),
                        resultSet.getString("Shipping_address"), resultSet.getString("last_name"),
                        resultSet.getString("first_name"), resultSet.getString("email"),
                        resultSet.getBoolean("privilege"),resultSet.getString("phone"));
                user.setUser_id(resultSet.getInt("user_id"));
                n++;
            }
            return user;
        }
        catch (Exception e){
            return null;
        }
    }


    public boolean updateUserUser_name(int user_id,String user_name){
        String sql;
            sql = "update user_info set user_name = \""+user_name+"\" where user_id = \""+user_id+"\"";
            return executeUpdate(sql);
    }

    public boolean updateUserPassword(int user_id,String password){
        String sql;
        sql = "update user_info set password = \""+password+"\" where user_id = \""+user_id+"\"";
        return executeUpdate(sql);
    }

    public boolean updateUserFirst_name(int user_id,String first_name){
        String sql;
        sql = "update user_info set first_name = \""+first_name+"\" where user_id = \""+user_id+"\"";
        return executeUpdate(sql);
    }

    public boolean updateUserLast_name(int user_id,String last_name){
        String sql;
        sql = "update user_info set last_name = \""+last_name+"\" where user_id = \""+user_id+"\"";
        return executeUpdate(sql);
    }

    public boolean updateUserEmail(int user_id,String email){
        String sql;
        sql = "update user_info set email = \""+email+"\" where user_id = \""+user_id+"\"";
        return executeUpdate(sql);
    }

    public boolean updateUserPhone(int user_id,String phone){
        String sql;
        sql = "update user_info set phone = \""+phone+"\" where user_id = \""+user_id+"\"";
        return executeUpdate(sql);
    }

    public boolean updateUserShipping_address(int user_id,String Shipping_address){
        String sql;
        sql = "update user_info set Shipping_address = \""+Shipping_address+"\" where user_id = \""+user_id+"\"";
        return executeUpdate(sql);
    }

    public User makeManager(User user){
        String sql;
        sql = "update user_info set privilege = \""+1+"\" where user_id = \""+user.getUser_id()+"\"";
        user.setPrivilege(true);
        if( executeUpdate(sql)){
            return user;
        }
        else return null;
    }

    public static void main(String[] args) throws SQLException {
        DBO dbo=new DBO();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
        Statement statement = connection.createStatement();

        /*dbo.insertRecordsInPUBLISHER();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.publisher");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("publisher_name") + resultSet.getString("publisher_address"));
        }
        //Book b= new Book(10,"harrypotter",5,2,"Art",);*/
        System.out.println(dbo.insertUser("MomenIbrahim", "1234567890", "Momen", "Ibrahim",
                "MomenIbrahim2000@gmail.com", "01220662659", "Alexandria"));
    }

}
