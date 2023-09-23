package com.appfilrouge.projetfilrouge.dto;


import com.appfilrouge.projetfilrouge.entities.Ad;

public class TicketDTO {
    private Long id;
    private String description;
    private boolean ticketStatus;
    private Float price;
    private Long orderTicketId;
    private Long adId;


    public TicketDTO(Long id, String description, boolean ticketStatus, Float price, Long orderTicketId, Long adId) {
        this.id = id;
        this.description = description;
        this.ticketStatus = ticketStatus;
        this.price = price;
        this.orderTicketId = orderTicketId;
        this.adId = adId;
    }

    public TicketDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(boolean ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getOrderTicketId() {
        return orderTicketId;
    }

    public void setOrderTicketId(Long orderTicketId) {
        this.orderTicketId = orderTicketId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

}
