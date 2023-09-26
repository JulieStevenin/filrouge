package com.appfilrouge.projetfilrouge.services;


import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.Seller;
import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.AdRepository;
import com.appfilrouge.projetfilrouge.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    public Ad createAd(Ad adRequest, UserDetails userDetails) {
        Ad ad = new Ad();

        User user = userService.findUserByMail(userDetails.getUsername());
        Seller seller = user.getSeller();

        ad.setName(adRequest.getName());
        ad.setCategory(adRequest.getCategory());
        ad.setCity(adRequest.getCity());
        ad.setEventDate(adRequest.getEventDate());
        ad.setPhoto(adRequest.getPhoto());
        ad.setSeller(seller);
        ad.getSeller().setUser(user);
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticketRequest : adRequest.getTickets()) {
            Ticket ticket = new Ticket();
            ticket.setPrice(ticketRequest.getPrice());
            ticket.setDescription(ticketRequest.getDescription());
            ticket.setAd(ad);
            tickets.add(ticket);
        }

        ad.setTickets(tickets);

        ad = adRepository.save(ad);

        for (Ticket ticket : tickets) {
            ticketRepository.save(ticket);
        }

        return ad;
    }


    public List<Ad> getAll() {
        return adRepository.findAll();
    }
}