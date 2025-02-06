package com.tva;

import com.tva.persistency.*;

public class Application {
    public static String clear = "\033[H\033[2J";
    public static void main(String[] args) {
        System.out.print(clear);
        UserDAO ud = new UserDAO();
        ud.readUserFromEmail("tvelezacosta5@gmail.co");
        ud.close();
        }

}
