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
    private String document;
    private Integer numberTickets;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private boolean onlineAdStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "adAddress_id")
    private AdAddress adAddress;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Ad() {
    }

    public Ad(String name, String document, Integer numberTickets, LocalDate eventDate, boolean adminAdCheck, String adminComment, boolean onlineAdStatus, Category category, AdAddress adAddress, List<Ticket> tickets, Seller seller) {
        this.name = name;
        this.document = document;
        this.numberTickets = numberTickets;
        this.eventDate = eventDate;
        this.adminAdCheck = adminAdCheck;
        this.adminComment = adminComment;
        this.onlineAdStatus = onlineAdStatus;
        this.category = category;
        this.adAddress = adAddress;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(Integer numberTickets) {
        this.numberTickets = numberTickets;
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

    public boolean isOnlineAdStatus() {
        return onlineAdStatus;
    }

    public void setOnlineAdStatus(boolean onlineAdStatus) {
        this.onlineAdStatus = onlineAdStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AdAddress getAdAddress() {
        return adAddress;
    }

    public void setAdAddress(AdAddress adAddress) {
        this.adAddress = adAddress;
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