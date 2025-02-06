package com.tva.services;
import java.sql.Connection;
import java.sql.ResultSet;

// Class with the CRUD operations for the "books" table in the db
public class BookService {
    private Connection con;
    public BookService() {
    }
    
    public BookService(Connection con) {
        this.con = con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void addBook(String title,String author, String genre, boolean lent){
        String query = "INSERT INTO books (title, author_id, genre_id, lent) VALUES (?,(SELECT a.id from authors a WHERE a.name = ?),(SELECT g.id from genres g WHERE g.name = ?),?);";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            st.setString(1, title);
            st.setString(2, author);
            st.setString(3, genre);
            st.setBoolean(4, lent);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);        
        }
    }

    public void readBooks(){
        String query = "SELECT b.title Titulo,a.name Autor,g.name Genero,b.lent Prestado FROM books b JOIN authors a ON b.author_id=a.id JOIN genres g ON b.genre_id=g.id;";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println("Titulo: "+rs.getString("Titulo")+" Autor: "+rs.getString("Autor")+" Genero: "+rs.getString("Genero")+" Prestado: "+rs.getBoolean("Prestado"));
            }
        } catch (Exception e) {
            System.out.println(e);}
    }

    public void updateBook(int id, String title,String author, String genre, boolean lent){
        String query = "UPDATE books SET title = ?, author_id = (SELECT id FROM authors WHERE name = ?),genre_id = (SELECT id FROM genres WHERE name = ?), lent = ? WHERE id = ?;";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            st.setString(2, title);
            st.setString(3, author);
            st.setString(4, title);
            st.setBoolean(5, lent);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);        }
    }

    public void deleteBook(int id){
        String query = "DELETE FROM books WHERE id = ?;";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);        
        }
    }

    public void lendBook(int book_id, String user){
        String query1 = "UPDATE books SET lent = TRUE WHERE id = ?;";
        String query2 = "INSERT INTO lent_books (book_id, user_id, lent_date) VALUES (?,(SELECT id FROM users WHERE email = ?),CURDATE());";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query1);
            st.setInt(1, book_id);
            st.executeUpdate();
            st = con.prepareStatement(query2);
            st.setInt(1, book_id);
            st.setString(2, user);
        } catch (Exception e) {
            System.out.println(e);        
        }
        
    }

    public void returnBook(int book_id, String user){
        String query1 = "UPDATE books SET lent = FALSE WHERE id = ?;";
        String query2 = "UPDATE lent_books SET return_date = CURDATE() WHERE book_id = ? AND user_id = (SELECT id FROM users WHERE  email = ?);";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query1);
            st.setInt(1, book_id);
            st.executeUpdate();
            st = con.prepareStatement(query2);
            st.setInt(1, book_id);
            st.setString(2, user);
        } catch (Exception e) {
            System.out.println(e);        
        }
        
    }

}
