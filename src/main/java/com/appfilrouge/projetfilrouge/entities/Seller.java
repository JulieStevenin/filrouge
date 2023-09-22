package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Ad> ads;

    public Seller() {
    }

    public Seller(User user, List<Ad> ads) {
        this.user = user;
        this.ads = ads;
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

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}