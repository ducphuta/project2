package com.example.pj2;

public class User {
    public String account;
    public String pass;
    public String name;

    public User(String account, String pass, String name) {
        this.account = account;
        this.pass = pass;
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
