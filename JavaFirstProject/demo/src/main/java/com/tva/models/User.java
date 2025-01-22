package com.tva.models;

import java.time.LocalDate;

public class User {
    protected String id;
    protected String userName;
    private String password;
    private boolean isAdmin;
    public LocalDate creationDate;
    public User(String id, String userName, String password, boolean isAdmin, LocalDate creationDate) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.creationDate = creationDate;
    }
    public User() {
    }
    public String getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
