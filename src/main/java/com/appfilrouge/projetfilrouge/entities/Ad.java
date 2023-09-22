package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private String category;
    private String city;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Ad() {
    }

    public Ad(String name, LocalDate eventDate, boolean adminAdCheck, String adminComment, String category, String city, List<Ticket> tickets, Seller seller) {
        this.name = name;
        this.eventDate = eventDate;
        this.adminAdCheck = adminAdCheck;
        this.adminComment = adminComment;
        this.category = category;
        this.city = city;
        this.tickets = tickets;
        this.seller = seller;
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
}