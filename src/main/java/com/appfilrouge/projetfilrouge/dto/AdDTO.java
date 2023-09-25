package com.appfilrouge.projetfilrouge.dto;

import com.appfilrouge.projetfilrouge.entities.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AdDTO {
    private Long id;
    private String name;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private String category;
    private Long sellerId;
    private String city;
    private int ticketQuantity;
    private Float totalPrice;

    private List<TicketDTO> tickets;

    public AdDTO() {
    }

    public AdDTO(Long id, String name, LocalDate eventDate, boolean adminAdCheck, String adminComment, String category, Long sellerId, String city, List<TicketDTO> tickets, int ticketQuantity, Float totalPrice) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.adminAdCheck = adminAdCheck;
        this.adminComment = adminComment;
        this.category = category;
        this.sellerId = sellerId;
        this.city = city;
        this.tickets = tickets;
        this.ticketQuantity=ticketQuantity;
        this.totalPrice=totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isAdminAdCheck() {
        return adminAdCheck;
    }

    public void setAdminAdCheck(boolean adminAdCheck) {
        this.adminAdCheck = adminAdCheck;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
