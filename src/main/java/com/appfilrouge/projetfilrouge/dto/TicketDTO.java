package com.appfilrouge.projetfilrouge.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private String description;
    private boolean ticketStatus;
    private Float price;
    private Long orderTicketId;
    private Long adId;
}
