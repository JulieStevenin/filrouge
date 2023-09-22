package com.appfilrouge.projetfilrouge.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    private Integer numberTickets;
    private LocalDate eventDate;
    private boolean adminAdCheck;
    private String adminComment;
    private boolean onlineAdStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adAddress_id")
    private AdAddress adAddress;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public void addTicket(Ticket ticket) {
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        ticket.setAd(this); // Assurez-vous de définir l'annonce pour le ticket.
        updateOnlineStatus(); // Mettez à jour l'état en ligne de l'annonce si nécessaire.
    }

    public void removeTicket(Ticket ticket) {
        if (tickets != null) {
            tickets.remove(ticket);
            ticket.setAd(null); // Assurez-vous de supprimer l'annonce pour le ticket.
            updateOnlineStatus(); // Mettez à jour l'état en ligne de l'annonce si nécessaire.
        }
    }
    public void updateOnlineStatus() {
        // Vérifiez si au moins un ticket est actif (statut true)
        boolean hasActiveTickets = tickets.stream().anyMatch(Ticket::isTicketStatus);
        onlineAdStatus = hasActiveTickets;
    }
    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", numberTickets=" + numberTickets +
                ", eventDate=" + eventDate +

                '}';
    }


}