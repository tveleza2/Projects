package com.tva.entities;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;

public class User {
    protected int id;
    protected String email;
    protected String name;
    protected String surename;
    protected String encodedPassword;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private boolean isAdmin;
    public Date birthDate;
    
    public User(String email, String name, String surename,Date birthDate, String password, boolean isAdmin){
        this.email = email;
        this.name = name;
        this.surename = surename;
        this.encodedPassword = this.encoder.encode(password);
        this.isAdmin = isAdmin;
        this.birthDate = birthDate;
    }

    public User() {
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public Date getBirthDay() {
        return birthDate;
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String userName) {
        this.email = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public void setEncodedPassword(String password) {
        this.encodedPassword = this.encoder.encode(password);
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setBirthDay(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "[Name=" + name+ " "+ surename +  ", E-mail=" + email + ", Birthday=" + birthDate + "]";
    }

}
