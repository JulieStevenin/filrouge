package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photo;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private String category;
    private String city;
   private int ticketQuantity;

    @JsonManagedReference(value="lien1")
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    @JsonBackReference(value="lien2")
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private String lname;
    private String fname;

    private String mail;

    public Ad() {
    }

    public Ad(String name, String photo, LocalDate eventDate, boolean adminAdCheck, String adminComment, String category, String city, int ticketQuantity, List<Ticket> tickets, Seller seller, String lname, String fname, String mail) {
        this.name = name;
        this.photo = photo;
        this.eventDate = eventDate;
        this.adminAdCheck = adminAdCheck;
        this.adminComment = adminComment;
        this.category = category;
        this.city = city;
        this.ticketQuantity = ticketQuantity;
        this.tickets = tickets;
        this.seller = seller;
        this.lname = lname;
        this.fname = fname;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isAdminAdCheck() {
        return adminAdCheck;
    }

    public void setAdminAdCheck(boolean adminAdCheck) {
        this.adminAdCheck = adminAdCheck;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}