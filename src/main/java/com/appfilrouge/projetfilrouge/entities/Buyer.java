package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Buyer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<OrderTicket> orderTickets;

    public Buyer() {
    }

    public Buyer(User user, List<OrderTicket> orderTickets) {
        this.user = user;
        this.orderTickets = orderTickets;
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
