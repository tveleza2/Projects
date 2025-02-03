package com.tva;
import java.sql.*;

import com.tva.services.*;

public class Application {
    public static String clear = "\033[H\033[2J";
    public static void main(String[] args) {
        System.out.print(clear);
        Connection con = dbConnector.getConnection();
        UserService userService = new UserService(con);
        // userService.readUserFromId(1);


        Date birthdate = Date.valueOf("2002-02-25");
        userService.addUser("tvelezacosta5@gmail.com", "Tomas", "Velez", birthdate, "AbCd1234", true);

        // userService.updateUser("tvelezacosta5@gmail.com", "Tomas", "Velez", birthdate, "AbCd1234", true);
    
        // userService.deleteUser("tvelezacosta5@gmail.com");

        }

}
