package com.appfilrouge.projetfilrouge.entities;


import com.fasterxml.jackson.annotation.*;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean ticketStatus;
    private Float price;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderTicket_id")
     @JsonIgnore
    private OrderTicket orderTicket;

    @ManyToOne(fetch = FetchType.LAZY)
   // @JsonIgnore
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Ticket() {
    }

    public Ticket(Long id, String description, boolean ticketStatus, Float price, OrderTicket orderTicket, Ad ad) {
        this.id = id;
        this.description = description;
        this.ticketStatus = ticketStatus;
        this.price = price;
        this.orderTicket = orderTicket;
        this.ad = ad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(boolean ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public OrderTicket getOrderTicket() {
        return orderTicket;
    }

    public void setOrderTicket(OrderTicket orderTicket) {
        this.orderTicket = orderTicket;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}