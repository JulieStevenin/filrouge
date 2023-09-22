package com.appfilrouge.projetfilrouge;

import com.appfilrouge.projetfilrouge.entities.*;
import com.appfilrouge.projetfilrouge.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication


public class ProjetFilRougeApplication implements CommandLineRunner {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private AdAddressRepository adAddressRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(ProjetFilRougeApplication.class, args);
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Ad ad1 = new Ad();
        OrderTicket orderTicket1 = new OrderTicket();
        Ticket ticket1 = new Ticket();
        AdAddress adAddress1 = new AdAddress();
        User user1 = new User();
        Buyer buyer1 = new Buyer();
        Category category1 = new Category();
        user1.setFname("safa");
        user1.setMail("elouaersafa6@gmail.com");
        user1.setLname("elouaer");
        user1.setPassword("mdp");
        user1.setPhoto("");
        user1.setBuyer(buyer1);
        user1.setBillingDetails(new BillingDetails());
        Seller seller1 = new Seller();
        seller1.setUser(user1);
        user1.setSeller(seller1);
        List<Ticket> tickets = ticketRepository.findAll() ;
        ad1.setName("annonce");
        ad1.setDocument("yes");
        ad1.setAdminAdCheck(true);
        ad1.setOnlineAdStatus(true);
        ad1.setTickets(tickets);
        ad1.setCategory(category1);
        ad1.setAdAddress(new AdAddress());
        ad1.setAdminComment("yes");
        ad1.setEventDate(LocalDate.now());
        ad1.setSeller(seller1);
        buyer1.setUser(user1);
        adAddress1.setCity("France");
        adAddress1.setStreet("Boulevard");
        adAddress1.setNumberStreet(23);
        ticket1.setPrice(202F);
        ticket1.setAd(ad1);
        ticket1.setDescription("yes");
        ticket1.setTicketStatus(true);

        adAddressRepository.save(adAddress1);
        ad1.setAdAddress(adAddress1);

        orderRepository.save(orderTicket1);
        categoryRepository.save(category1);
        adRepository.save(ad1);
        ticketRepository.save(ticket1);
        System.out.println(ticket1);

        userRepository.save(user1);
        sellerRepository.save(seller1);
        buyerRepository.save(buyer1);

    }

}
