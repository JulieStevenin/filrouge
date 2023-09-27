package com.appfilrouge.projetfilrouge.dto;

import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.entities.Seller;
import jakarta.persistence.Column;

public class UserDTO {

    private Long id;
    private String fname;
    private String lname;
    @Column(unique = true)
    private String mail;
    private String photo;
    private String password;

    private Buyer buyer;

    private Seller seller;

    public UserDTO() {
    }

    public UserDTO(Long id, String fname, String lname, String mail, String photo, String password, Buyer buyer, Seller seller) {
    }

    public UserDTO(String fname, String lname, String mail, String photo, String password, Buyer buyer, Seller seller) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.photo = photo;
        this.password = password;
        this.buyer = buyer;
        this.seller = seller;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}