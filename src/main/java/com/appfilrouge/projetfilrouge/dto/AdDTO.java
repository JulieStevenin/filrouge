package com.appfilrouge.projetfilrouge.dto;

import com.appfilrouge.projetfilrouge.entities.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class AdDTO {
    private Long id;
    private String name;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private String category;
    private Long sellerId;
    private String city;

    private List<Ticket> tickets;


}
