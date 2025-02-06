package com.tva;

import com.tva.persistency.*;
import com.tva.entities.*;

public class Application {
    public static String clear = "\033[H\033[2J";
    public static void main(String[] args) {
        System.out.print(clear);
        UserDAO ud = new UserDAO();
        ud.close();
        }

}
