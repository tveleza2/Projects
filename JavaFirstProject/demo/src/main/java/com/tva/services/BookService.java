package com.tva.services;
import java.sql.Connection;
import java.sql.ResultSet;


public class BookService {
    private Connection con;
    public BookService() {
    }
    public BookService(Connection con) {
        this.con = con;
    }
    public void addBook(String title,String author, String genre, boolean lent){
        String query = "INSERT INTO books (title, author_id, genre_id, lent) VALUES (?,(SELECT a.id from authors a WHERE a.name = ?),(SELECT g.id from genre g WHERE g.name = ?),?);";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            st.setString(1, title);
            st.setString(2, author);
            st.setString(3, genre);
            st.setBoolean(4, lent);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);        }
    }
    public void readBooks(){
        String query = "SELECT b.title Titulo,a.name Autor,g.name Genero,b.lent Prestado FROM books b JOIN authors a ON b.author_id=a.id JOIN genre g ON b.genre_id=g.id;";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                System.out.println("Titulo: "+rs.getString("Titulo")+" Autor: "+rs.getString("Autor")+" Genero: "+rs.getString("Genero")+" Prestado: "+rs.getBoolean("Prestado"));
            }
        } catch (Exception e) {
            System.out.println(e);        }
    }
}
