package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonBackReference(value = "seller")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonManagedReference(value="lien2")
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Ad> ads;

    private String description="par defaut";

    public Seller() {
    }

    public Seller(User user, List<Ad> ads, String description) {
        this.user = user;
        this.ads = ads;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
