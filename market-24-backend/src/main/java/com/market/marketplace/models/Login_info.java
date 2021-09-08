package com.market.marketplace.models;

public class Login_info {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login_info{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
