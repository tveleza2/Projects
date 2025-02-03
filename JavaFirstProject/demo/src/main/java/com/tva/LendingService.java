package com.tva;

import java.sql.Connection;

public class LendingService {
    private Connection con;
    public LendingService() {
    }

    public LendingService(Connection con) {
        this.con = con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void lendBook(int bookId, int userId) {
        String query = "INSERT INTO lendings (book_id, user_id) VALUES (?,?);";
        try {
            java.sql.PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, bookId);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
