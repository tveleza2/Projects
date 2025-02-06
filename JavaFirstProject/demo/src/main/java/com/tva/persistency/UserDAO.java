package com.tva.persistency;

import com.tva.entities.User;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class UserDAO extends DAO{
    public UserDAO(){
        try {
            connectDataBase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addUser(User newUser) {
        String query = "INSERT INTO users (email, name, surename, birthdate, password, isAdmin) VALUES (?,?,?,?,?,?);";
        try {
            noOutputQuery(query, newUser.getEmail(),newUser.getName(),newUser.getSurename(),newUser.getBirthDay(),newUser.getEncodedPassword(),newUser.isAdmin());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readUsers() {
        String query = "SELECT email, name, surename, birthdate, password, isAdmin FROM users;";
        List<User> users = new ArrayList<>();
        try {
            outputQuery(query, (Object[])null);
            if (!resultSet.isBeforeFirst() ) {    
                System.out.println("No data"); 
            }
            while(resultSet.next()){
                User newUser = new User(resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("surename"), resultSet.getDate("birthdate"), resultSet.getString("password"),resultSet.getBoolean("isAdmin"));
                users.add(newUser);
            }
            Iterator <User> it = users.iterator();
            while(it.hasNext()){
                System.out.println(it.next().toString());
            }

        } catch (Exception e) {
            System.out.println(e);}
    }

    public void readUserFromId(int id) {
        String query = "SELECT email, name, surename, birthdate, password, isAdmin FROM users WHERE id = ?;";
        List<User> users = new ArrayList<>();
        try {
            outputQuery(query, id);
            if (!resultSet.isBeforeFirst() ) {    
                System.out.println("No data"); 
            }
            while(resultSet.next()){
                User newUser = new User(resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("surename"), resultSet.getDate("birthdate"), resultSet.getString("password"),resultSet.getBoolean("isAdmin"));
                users.add(newUser);
            }
            Iterator <User> it = users.iterator();
            while(it.hasNext()){
                System.out.println(it.next().toString());
            }

        } catch (Exception e) {
            System.out.println(e);}
    }

    public void readUserFromEmail(String email) {
        String query = "SELECT email, name, surename, birthdate, password, isAdmin FROM users WHERE email = ?;";
        List<User> users = new ArrayList<>();
        try {
            outputQuery(query, email);
            if (!resultSet.isBeforeFirst() ) {    
                System.out.println("No data"); 
            }
            while(resultSet.next()){
                User newUser = new User(resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("surename"), resultSet.getDate("birthdate"), resultSet.getString("password"),resultSet.getBoolean("isAdmin"));
                users.add(newUser);
            }
            Iterator <User> it = users.iterator();
            while(it.hasNext()){
                System.out.println(it.next().toString());
            }

        } catch (Exception e) {
            System.out.println(e);}
    }
    
    public void updateUser(String email,User newUser) {
        String query = "UPDATE users SET email = ?, name = ?, surename = ?, birthdate = ?, password = ?, isAdmin = ? WHERE email = ?;";
        try {
            noOutputQuery(query, newUser.getEmail(),newUser.getName(),newUser.getSurename(),newUser.getBirthDay(),newUser.getEncodedPassword(),newUser.isAdmin(),newUser.getEmail());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteUser(String email) {
        String query = "DELETE FROM users WHERE email = ?;";
        try {
            noOutputQuery(query, email);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
