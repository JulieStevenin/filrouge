package com.appfilrouge.projetfilrouge.dto;

public class LoginRequest {

    private  String mail;
    private  String password;

    public LoginRequest() {
    }

    public LoginRequest(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}