package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class OrderTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "orderTicket", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    private Float totalPrice;
    private boolean validated;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public OrderTicket() {
    }

    public OrderTicket(List<Ticket> tickets, Float totalPrice, boolean validated, Buyer buyer) {
        this.tickets = tickets;
        this.totalPrice = totalPrice;
        this.validated = validated;
        this.buyer = buyer;
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

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}