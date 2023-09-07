package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AdAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer numberStreet;
    private String city;

    @OneToMany(mappedBy = "adAddress")
    private List<Ad> ads;

    public AdAddress() {
    }

    public AdAddress(String street, Integer numberStreet, String city, List<Ad> ads) {
        this.street = street;
        this.numberStreet = numberStreet;
        this.city = city;
        this.ads = ads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberStreet() {
        return numberStreet;
    }

    public void setNumberStreet(Integer numberStreet) {
        this.numberStreet = numberStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}