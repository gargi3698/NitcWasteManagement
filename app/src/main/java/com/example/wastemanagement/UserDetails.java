package com.example.wastemanagement;

public class UserDetails {
    public String username,email,phone,pass;

    public UserDetails() {}

    public UserDetails(String username, String email,String phone, String pass) {
        this.email = email;
        this.username = username;
        this.phone=phone;
        this.pass=pass;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
