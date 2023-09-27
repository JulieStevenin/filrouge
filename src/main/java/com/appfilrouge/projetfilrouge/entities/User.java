package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;

    @Column(unique = true)
    private String mail;
    private String password;
    private String photo;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Role> roleList;

    @JsonManagedReference(value = "buyer")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Buyer buyer;
    @JsonManagedReference(value = "seller")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Seller seller;

    @JsonManagedReference(value = "user")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private BillingDetails billingDetails;

    public User() {
    }

    public User(String fname, String lname, String mail, String password, String photo, Buyer buyer, Seller seller, BillingDetails billingDetails) {
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
        this.password = password;
        this.photo = photo;
        this.buyer = buyer;
        this.seller = seller;
        this.billingDetails = billingDetails;
    }

    public User(String username) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //boucler sur notre liste de roles ci-dessus
        //créer une liste contenant plusieurs SimpleGrantedAuthority
        //retourner cette liste de SimplegrantedAuthority
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : this.roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }
}