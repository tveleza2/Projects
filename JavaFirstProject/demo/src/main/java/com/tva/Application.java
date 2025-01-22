package com.tva;
import java.sql.Connection;

import com.tva.*;
import com.tva.services.BookService;
import com.tva.services.dbConnector;

public class Application {
    public static String clear = "\033[H\033[2J";
    public static void main(String[] args) {
        System.out.print(clear);
        dbConnector db = new dbConnector();
        Connection con = db.getConnection();
        BookService bookService = new BookService(con);
        }
}
