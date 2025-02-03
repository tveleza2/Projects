package com.tva.services;
import java.sql.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {
    protected BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private Connection con;
    public UserService() {
    }
    public UserService(Connection con) {
        this.con = con;
    }
    public void setCon(Connection con) {
        this.con = con;
    }
    public void addUser(String email, String name, String surename, Date birthDate, String password, boolean isAdmin) {
        String query = "INSERT INTO users (email, name, surename, birthdate, password, isAdmin) VALUES (?,?,?,?,?,?);";
        try {
            java.sql.PreparedStatement st = this.con.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, name);
            st.setString(3, surename);
            st.setDate(4, birthDate);
            String encodedPassword = this.encoder.encode(password);
            st.setString(5,encodedPassword);
            st.setBoolean(6, isAdmin);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readUserFromId(int id) {
        String query = "SELECT concat(name, ' ' ,surename) AS Nombre, email AS Correo, isAdmin AS Administrador FROM users WHERE id = ?;";
        try {
            java.sql.PreparedStatement st = this.con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println("Nombre: "+rs.getString("Nombre")+" Correo: "+rs.getString("Correo")+" Administrador: "+rs.getBoolean("Administrador"));
            }
        } catch (Exception e) {
            System.out.println(e);}
    }

    public void readUserFromEmail(String email) {
        String query = "SELECT concat(name, ' ' ,surename) AS Nombre, email AS Correo, isAdmin AS Administrador FROM users WHERE email = ?;";
        try {
            java.sql.PreparedStatement st = this.con.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println("Nombre: "+rs.getString("Nombre")+" Correo: "+rs.getString("Correo")+" Administrador: "+rs.getBoolean("Administrador"));
            }
        } catch (Exception e) {
            System.out.println(e);}
    }

    private String passwordFromEmail(String email) {
        String query = "SELECT password FROM users WHERE email = ?;";
        try {
            java.sql.PreparedStatement st = this.con.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return rs.getString("password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }


    public void updateUser(String email, String name, String surename, Date birthDate, String password, boolean isAdmin) {
        String query = "UPDATE users SET email = ?, name = ?, surename = ?, birthdate = ?, password = ?, isAdmin = ? WHERE email = ?;";
        try {
            java.sql.PreparedStatement st = this.con.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, name);
            st.setString(3, surename);
            st.setDate(4, birthDate);
            String encodedPassword = this.encoder.encode(password);
            st.setString(5,encodedPassword);
            st.setBoolean(6, isAdmin);
            st.setString(7, email);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteUser(String email) {
        String query = "DELETE FROM users WHERE email = ?;";
        try {
            java.sql.PreparedStatement st = this.con.prepareStatement(query);
            st.setString(1, email);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean verifyPassword(String email, String password){
        return this.encoder.matches(password,passwordFromEmail(email));
    }



}
