package com.appfilrouge.projetfilrouge.dto;

public class UserDTO {

    private Long id;
    private String fname;
    private String lname;
    private String mail;
    private String photo;

    private String password;

    public UserDTO() {
    }

    public UserDTO(Long id, String fname, String lname, String mail, String photo, String password) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.photo = photo;
        this.password=password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
