package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adType;
    private String adStyle;

    @OneToMany(mappedBy = "category")
    private List<Ad> ads;

    public Category() {
    }

    public Category(String adType, String adStyle, List<Ad> ads) {
        this.adType = adType;
        this.adStyle = adStyle;
        this.ads = ads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdStyle() {
        return adStyle;
    }

    public void setAdStyle(String adStyle) {
        this.adStyle = adStyle;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
