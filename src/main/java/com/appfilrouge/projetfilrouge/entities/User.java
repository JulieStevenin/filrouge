package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String mail;
    private String password;
    private String photo;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Buyer buyer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Seller seller;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private BillingDetails billingDetails;

    public User() {
    }

    public User(String fname, String lname, String mail, String password, String photo, Buyer buyer, Seller seller, BillingDetails billingDetails) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.password = password;
        this.photo = photo;
        this.buyer = buyer;
        this.seller = seller;
        this.billingDetails = billingDetails;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }
}
