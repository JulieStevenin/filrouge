package com.appfilrouge.projetfilrouge.entities;


import com.fasterxml.jackson.annotation.*;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean ticketStatus;
    private Float price;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderTicket_id")
   @JsonIgnore
    private OrderTicket orderTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ad_id")
    private Ad ad;


}