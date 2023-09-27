package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.entities.BillingDetails;
import com.appfilrouge.projetfilrouge.entities.OrderTicket;
import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.BillingDetailsRepository;
import com.appfilrouge.projetfilrouge.repositories.OrderRepository;
import com.appfilrouge.projetfilrouge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;


@Service
public class OrderTicketService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<OrderTicket> getOrderById(Long id) {
        return orderRepository.findById(id);
    }





    public OrderTicket findOrderTicketsByTicketId(Long id) {
        return orderRepository.findOrderTicketsByTicketId(id);
    }

    public OrderTicket findOrderbySellerId(Long id) {
        return orderRepository.findOrderTicketBySellerId(id);
    }

    public OrderTicket createOrder(OrderTicket orderTicket) {
        orderRepository.save(orderTicket);
        return orderTicket;
    }


    public void validateOrderSimple (Long id, String cardCode) {
        if (!cardCode.isEmpty()) {
            OrderTicket order = orderRepository.findById(id).orElse(null);
           order.setValidated(true);
           orderRepository.save(order);
        }
    }

    public boolean validateOrder(Long Id, User user, String cardCode, String securityCode, String cardDate) {
        OrderTicket order = orderRepository.findById(Id).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Commande introuvable.");
        }

        BillingDetails billingDetails = user.getBillingDetails();
        if (billingDetails == null) {
            throw new IllegalArgumentException("Moyen de paiement non renseigné.");
        }

        if (!isValidCardCode(cardCode) || !isValidSecurityCode(securityCode) || !isValidCardDate(cardDate)) {
            throw new IllegalArgumentException("Le code bancaire, le code de sécurité ou la date de validité ne sont pas valides.");
        }

        order.setValidated(true);
        orderRepository.save(order);

        return true;
    }

    private boolean isValidCardCode(String cardCode) {
        return cardCode != null && cardCode.matches("\\d{16}");
    }

    private boolean isValidSecurityCode(String securityCode) {
        return securityCode != null && securityCode.matches("\\d{3}");
    }

    private boolean isValidCardDate(String cardDate) {
        return cardDate != null && cardDate.matches("\\d{2}/\\d{4}");
    }

    public Map<String, Object> createInvoice(OrderTicket orderTicket) {
        Map<String, Object> purchaseInvoice = new HashMap<>();

        List<Ticket> tickets = orderTicket.getTickets();
        Float totalPrice = orderTicket.getTotalPrice();
        User buyer = orderTicket.getBuyer().getUser();
        User seller = orderTicket.getSeller().getUser();
        LocalDate orderDate = orderTicket.getOrderDate();

        List<Map<String, Object>> ticketDetails = new ArrayList<>();
        for (Ticket ticket : tickets) {
            Map<String, Object> ticketDetail = new HashMap<>();
            ticketDetail.put("description", ticket.getDescription());
            ticketDetail.put("price", ticket.getPrice());
            ticketDetails.add(ticketDetail);
        }

        purchaseInvoice.put("tickets", ticketDetails);
        purchaseInvoice.put("totalPrice", totalPrice);
        purchaseInvoice.put("buyerName", buyer.getFname() + " " + buyer.getLname());
        purchaseInvoice.put("sellerName", seller.getFname() + " " + seller.getLname());
        purchaseInvoice.put("orderDate", orderDate);

        return purchaseInvoice;
    }
}