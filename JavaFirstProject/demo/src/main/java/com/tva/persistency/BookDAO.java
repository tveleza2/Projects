package com.tva.persistency;

import java.util.List;
import java.util.ArrayList;

import com.tva.entities.Book;

public class BookDAO extends DAO {
    public BookDAO(){
        try {
            connectDataBase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author_id, genre_id, lent) VALUES (?,?,?,?);";
        try {
            noOutputQuery(query, book.getTitle(), book.getAuthorId(), book.getGenreId(), book.isLent());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readBooks(){
        String query = "SELECT b.id ID, b.title Titulo, b.author_id Autor, b.genre_id Genero, b.lent Prestado FROM books b;";
        List<Book> books = new ArrayList<>();
        try {
            outputQuery(query, (Object[])null);
            while(this.resultSet.next()){
                Book book = new Book();
                book.setId(this.resultSet.getInt("ID"));
                book.setTitle(this.resultSet.getString("Titulo"));
                book.setAuthorId(this.resultSet.getInt("Autor"));
                book.setGenreId(this.resultSet.getInt("Genero"));
                book.setLent(this.resultSet.getBoolean("Prestado"));
                books.add(book);
            }
            for (Book book : books) {
                System.out.println(book.toString());
            }
        } catch (Exception e) {
            System.out.println(e);}
    }

    public void updateBook(int id, Book newBook){
        String query = "UPDATE books SET title = ?, author_id = ?,genre_id = ?, lent = ? WHERE id = ?;";
        try {
            noOutputQuery(query, newBook.getTitle(), newBook.getAuthorId(), newBook.getGenreId(), newBook.isLent(), id);
        } catch (Exception e) {
            System.out.println(e);        }
    }

    public void deleteBook(int id){
        String query = "DELETE FROM books WHERE id = ?;";
        try {
            noOutputQuery(query, id);
        } catch (Exception e) {
            System.out.println(e);        
        }
    }

}
