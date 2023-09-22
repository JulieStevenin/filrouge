package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardCode;
    private String securityCode;
    private String cardDate;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BillingDetails() {
    }

    public BillingDetails(String cardCode, String securityCode, String cardDate, User user) {
        this.cardCode = cardCode;
        this.securityCode = securityCode;
        this.cardDate = cardDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
