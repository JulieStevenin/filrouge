package com.appfilrouge.projetfilrouge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
        private LocalDate eventDate;
        private boolean adminAdCheck;
        private String adminComment;
        private String category;
        private String city;
        @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
        private List<Ticket> tickets;
        @ManyToOne
        @JoinColumn(name = "seller_id")
        private Seller seller;




        /* public void addTicket(Ticket ticket) {
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
         }*/
        @Override
        public String toString() {
                return "Ad{" +
                        "id=" + id +
                        ", name='" + name + '\'' +

                        ", eventDate=" + eventDate +

                        '}';
        }




    }