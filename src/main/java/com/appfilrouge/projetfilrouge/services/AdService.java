package com.appfilrouge.projetfilrouge.services;


import com.appfilrouge.projetfilrouge.entities.*;
import com.appfilrouge.projetfilrouge.repositories.AdRepository;
import com.appfilrouge.projetfilrouge.repositories.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;


    public Ad createAd(Ad adRequest, UserDetails userDetails) {
        Ad ad = new Ad();

        User user = userService.findUserByMail(userDetails.getUsername());
        Seller seller = user.getSeller();


        ad.setName(adRequest.getName());
        ad.setFname(user.getFname());
        ad.setLname(user.getLname());
        ad.setCategory(adRequest.getCategory());
        ad.setCity(adRequest.getCity());
        ad.setEventDate(adRequest.getEventDate());
        ad.setPhoto(adRequest.getPhoto());
        ad.setMail(user.getMail());
        ad.setSeller(seller);
git 
        OrderTicket orderTicket = new OrderTicket();
        orderTicket.setSeller(seller);
        orderTicket.setTotalPrice(0F);
        orderRepository.save(orderTicket);

        List<Ticket> tickets = new ArrayList<>();
        Float totalPrice=0F;

        for (Ticket ticketRequest : adRequest.getTickets()) {
            Ticket ticket = new Ticket();
            ticket.setPrice(ticketRequest.getPrice());
            totalPrice += ticketRequest.getPrice();
            ticket.setDescription(ticketRequest.getDescription());
            ticket.setAd(ad);
            ticket.setOrderTicket(orderTicket);
            tickets.add(ticket);
        }

        orderTicket.setTotalPrice(totalPrice);
        orderRepository.save(orderTicket);
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

    public User findbySellerId(Long id) {
        return adRepository.findBySellerId(id);
    }

    public List<Ad> findAdByMail(String mail) {
        return adRepository.findAdsByMail(mail);
    }
}