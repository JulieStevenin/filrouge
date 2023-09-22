package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Buyer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<OrderTicket> orderTickets;

    private String description;

    public List<OrderTicket> getOrderTickets() {
        return orderTickets;
    }

    public void setOrderTickets(List<OrderTicket> orderTickets) {
        this.orderTickets = orderTickets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Buyer() {
    }

    public Buyer(User user, List<OrderTicket> orderTickets, String description) {
        this.user = user;
        this.orderTickets = orderTickets;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderTicket> getOrders() {
        return orderTickets;
    }

    public void setOrders(List<OrderTicket> orderTickets) {
        this.orderTickets = orderTickets;
    }
}
