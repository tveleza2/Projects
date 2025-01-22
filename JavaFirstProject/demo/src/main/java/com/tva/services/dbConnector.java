package com.tva.services;
import java.sql.*;

public class dbConnector {
    private static String url="jdbc:mysql://localhost:3306/library_db";
    private static String userName = "root";
    private static String password = "Dbroot2024.";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
