package com.appfilrouge.projetfilrouge.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refOrder;
    @OneToMany(mappedBy = "orderTicket", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    private Float totalPrice;

    private boolean validated;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public OrderTicket() {
    }

    public OrderTicket(String refOrder, List<Ticket> tickets, Float totalPrice, Buyer buyer) {
        this.refOrder = refOrder;
        this.tickets = tickets;
        this.totalPrice = totalPrice;
        this.buyer = buyer;
    }

    public String getRefOrder() {
        return refOrder;
    }

    public void setRefOrder(String refOrder) {
        this.refOrder = refOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}