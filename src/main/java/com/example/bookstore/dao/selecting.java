package com.example.bookstore.dao;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Publisher;

import java.security.KeyStore;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class selecting implements selectingInterface{
    private Connection connection;
    private Statement statement;
    public selecting(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "test", "test");
            statement = connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Publisher getPublisher(int publisher_id){
        Publisher p = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.publisher WHERE publisher_id="+publisher_id);
            resultSet.next();
            p =new Publisher(resultSet.getInt("publisher_id"),resultSet.getString("publisher_address"),
                    resultSet.getString("publisher_name"),resultSet.getString("publisher_phone"));
            return p;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return p;
    }

    public Author getAuthor(int author_id){
        Author author = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.authors WHERE author_id="+author_id);
            resultSet.next();
            author =new Author(resultSet.getInt("author_id"),resultSet.getString("author_name"));
            return author;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return author;
    }

    public List<Author> getAuthorByName(String author_Name){
        List<Author> authors = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.authors WHERE author_name LIKE \"%"+author_Name+"%\"");
            while (resultSet.next()) {
                authors.add(new Author(resultSet.getInt("author_id"),resultSet.getString("author_name")));
            }
            return authors;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return authors;
    }

    public List<Publisher> getPublisherByName(String publisher_name){
        List<Publisher> publishers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.publisher WHERE publisher_name LIKE \"%"+publisher_name+"%\"");
            while (resultSet.next()) {
                publishers.add(new Publisher(resultSet.getInt("publisher_id"),resultSet.getString("publisher_address")
                        ,resultSet.getString("publisher_name"),resultSet.getString("publisher_phone")));
            }
            return publishers;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return publishers;
    }

    public List<Integer> getAuthorsIDOfBook(int isbn){
        List<Integer> authors=new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.book_authors WHERE isbn="+isbn);
            while (resultSet.next()) {
                authors.add(resultSet.getInt("author_id"));
            }
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return authors;
    }

    public List<Author> getAuthorsOfBook(int isbn){
        List<Author> authors=new ArrayList<>();
        List<Integer> authorsIDs=getAuthorsIDOfBook(isbn);
        for (int i = 0; i < authorsIDs.size(); i++) {
            authors.add(getAuthor(authorsIDs.get(i)));
        }
        return authors;
    }

    public Book getBook(int isbn){
        Book b = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.book WHERE isbn="+isbn);
            resultSet.next();
            b =new Book(resultSet.getInt("isbn"),resultSet.getString("title"),
                    resultSet.getInt("num_of_copies"),resultSet.getInt("threshold"),
                    resultSet.getString("category"),null,resultSet.getString("publication_year"),
                    resultSet.getFloat("selling_price"),null);
            Publisher publisher=getPublisher(resultSet.getInt("publisher_id"));
            b.setPublisherName(publisher);
            List<Author> authors=getAuthorsOfBook(isbn);
            b.setAuthors(authors);
            return b;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return b;
    }

    public List<Book> getBooksByTitle(String title){
        List<Book> books=new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT isbn FROM bookstore.book WHERE title LIKE \"%"+title+"%\"");
            while (resultSet.next()) {
                books.add(getBook(resultSet.getInt("isbn")));
            }
            return books;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchBookWithISBNAndTittle(Integer isbn,String title){
        List<Book> books=new ArrayList<>();
        if (isbn==null&&title==null){
            return books;
        }
        else if (isbn!=null&&title!=null){
            return getBooksByISBNAndTitle(isbn,title);
        }
        else if (title!=null){
            return getBooksByTitle(title);
        }
        else {
            Book b=getBook(isbn);
            if (b!=null)
                books.add(b);
            return books;
        }
    }

    public List<Book> getBooksByISBNAndTitle(int isbn,String title){
        List<Book> books=new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT isbn FROM bookstore.book WHERE title LIKE \"%"+title+"%\" AND isbn="+isbn);
            while (resultSet.next()) {
                books.add(getBook(resultSet.getInt("isbn")));
            }
            return books;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchBooksByAuthor(Author author){
        List<Book> books=new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT isbn FROM bookstore.book_authors WHERE author_id = \""+author.getAuthor_id()+"\"");
            while (resultSet.next()) {
                books.add(getBook(resultSet.getInt("isbn")));
            }
            return books;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchBooksByAuthor(String author_name){
        List<Author> authors = getAuthorByName(author_name);
        List<Book> books=new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            books.addAll(searchBooksByAuthor(authors.get(i)));
        }
        return books;
    }

    public List<Book> searchBooksByPublisher(String publisher_name){
        List<Publisher> publishers = getPublisherByName(publisher_name);
        List<Book> books=new ArrayList<>();
        for (int i = 0; i < publishers.size(); i++) {
            books.addAll(searchBooksByPublisher(publishers.get(i)));
        }
        return books;
    }

    public List<Book> searchBooksByAuthorName(String author_Name){
        List<Author> authors= getAuthorByName(author_Name);
        List<Book> books=new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            books.addAll(searchBooksByAuthor(authors.get(i)));
        }
        return books;
    }

    public List<Book> searchBooksByPublisher(Publisher publisher){
        List<Book> books=new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT isbn FROM bookstore.book WHERE publisher_id = \""+publisher.getPublisher_id()+"\"");
            while (resultSet.next()) {
                books.add(getBook(resultSet.getInt("isbn")));
            }
            return books;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return books;
    }

    public List<Book> searchBooksByCategory(String category){
        List<Book> books=new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT isbn FROM bookstore.book WHERE category LIKE \"%"+category+"%\"");
            while (resultSet.next()) {
                temp.add(resultSet.getInt("isbn"));
            }

            for (Integer i : temp) {
                books.add(getBook(i));
            }
            return books;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return books;
    }


    //Map of Attribute : value of that attribute
    public List<Book> searchBookWithCategoryAuthorPublisherName(Map<String,String> hash){
        List<Book> books=new ArrayList<>();
        try {
            String sql="SELECT isbn FROM bookstore.book WHERE ";
            for (Map.Entry<String, String> set :
                    hash.entrySet()) {
                sql+=set.getKey() + " = " + set.getValue();
            }
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                books.add(getBook(resultSet.getInt("isbn")));
            }
            return books;
        }
        catch (Exception e){
            //e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Author> getAllAuthors() {
        String query = "Select * from authors";
        List<Author> authors = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
                authors.add(new Author(resultSet.getInt("author_id"),
                        resultSet.getString("author_name")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        String query = "Select * from publisher";
        List<Publisher> publishers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
                publishers.add(new Publisher(resultSet.getInt("publisher_id"),
                        resultSet.getString("publisher_address"),
                        resultSet.getString("publisher_name"),
                        resultSet.getString("publisher_phone")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    public static void main(String[] args) {
        selecting s= new selecting();
        Publisher p=s.getPublisher(5);
        System.out.println(p==null);
        System.out.println(s.getBook(5)==null);
    }
}


/*public List<Publisher> getPublishers(int publisher_id){
        List<Publisher> publishers=new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookstore.publisher WHERE publisher_id="+publisher_id);
            while (resultSet.next()) {
                Publisher p =new Publisher(resultSet.getInt("publisher_id"),resultSet.getString("publisher_address"),
                        resultSet.getString("publisher_name"),resultSet.getString("publisher_phone"));
                publishers.add(p);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return publishers;
    }*/
