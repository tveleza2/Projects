package com.tva.persistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
    protected Connection connection = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement statement = null;
    private final String HOST = "127.0.0.1";
    private final String PORT = "3306";
    private final String USER = "root";
    private final String PASSWORD = "Dbroot2024.";
    private final String DATABASE = "library_db";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void connectDataBase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE+"?useSSL=false";
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected void disconnectDataBase() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing ResultSet: " + e.getMessage());
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing Statement: " + e.getMessage());
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing Connection: " + e.getMessage());
        }
    }

    protected void prepareStatement(String sql, Object... params) throws SQLException {
        statement = connection.prepareStatement(sql);
        
        // Set parameters dynamically
        if(params == null) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) params[i]);
            } else if (params[i] instanceof String) {
                statement.setString(i + 1, (String) params[i]);
            } else if (params[i] instanceof Double) {
                statement.setDouble(i + 1, (Double) params[i]);
            } else if (params[i] instanceof Boolean) {
                statement.setBoolean(i + 1, (Boolean) params[i]);
            } else if (params[i] instanceof Long) {
                statement.setLong(i + 1, (Long) params[i]);
            } else if (params[i] instanceof Float) {
                statement.setFloat(i + 1, (Float) params[i]);
            } else if (params[i] instanceof java.sql.Date) {
                statement.setDate(i + 1, (java.sql.Date) params[i]);
            } else if (params[i] instanceof java.sql.Timestamp) {
                statement.setTimestamp(i + 1, (java.sql.Timestamp) params[i]);
            } else {
                statement.setObject(i + 1, params[i]); // Fallback for unknown types
            }
        }

    }

    protected void noOutputQuery(String sql, Object... params) throws Exception {
        try {
            if (connection == null) {
                connectDataBase();
            }
            prepareStatement(sql, params);
            statement.executeUpdate();
            statement = null;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
        
    }

    protected void outputQuery(String sql, Object... params) throws Exception {
        try {
            if (connection == null) {
                connectDataBase();
            }
            prepareStatement(sql, params);
            resultSet = statement.executeQuery();
            statement = null;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public void close() {
        disconnectDataBase();
    }

}



