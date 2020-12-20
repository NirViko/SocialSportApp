package com.example.socialsportapp;

import android.provider.ContactsContract;

import java.util.Date;

public class User {
    String full_name;
    String password;
    String email;
    String date;


    public User(String full_name, String password, String email, String date)
    {
        this.full_name = full_name;
        this.password = password;
        this.email = email;
        this.date = date;
    }
    public User()
    {

    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }




}
