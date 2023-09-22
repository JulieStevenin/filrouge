package com.appfilrouge.projetfilrouge.dto;

import com.appfilrouge.projetfilrouge.entities.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class AdDTO {
    private Long id;
    private String name;
    private String document;
    private Integer numberTickets;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private boolean onlineAdStatus;
    private Category category;
    private Long categoryId;
    private Long adAddressId;
    private Long sellerId;
    private AdAddress adAddress;
    private List<Ticket> tickets;


}
