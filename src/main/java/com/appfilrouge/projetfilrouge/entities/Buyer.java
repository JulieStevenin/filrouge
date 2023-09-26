package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Buyer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String lname;

    private String fname;
    @JsonBackReference(value = "buyer")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<OrderTicket> orderTickets;

    private String description="par defaut";

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

    public Buyer(String lname, String fname, User user, List<OrderTicket> orderTickets, String description) {
        this.lname = lname;
        this.fname = fname;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
