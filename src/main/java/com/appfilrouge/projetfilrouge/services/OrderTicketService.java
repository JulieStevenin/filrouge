package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.entities.BillingDetails;
import com.appfilrouge.projetfilrouge.entities.OrderTicket;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.BillingDetailsRepository;
import com.appfilrouge.projetfilrouge.repositories.OrderRepository;
import com.appfilrouge.projetfilrouge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderTicketService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderTicket getOrderById(Long id) {
        Optional<OrderTicket> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            return null;
        }
    }

    public OrderTicket createOrder(OrderTicket orderTicket) {
        if (orderTicket.getTickets() == null || orderTicket.getTickets().isEmpty()) {
            throw new IllegalArgumentException("Vous ne pouvez pas passer commande car la liste d'achat est vide.");
        } else {
            orderRepository.save(orderTicket);
            return orderTicket;
        }
    }

    public boolean validateOrder(Long orderId, User user, String cardCode, String securityCode, String cardDate) {
        // Récupérez la commande associée à orderId
        OrderTicket order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Commande introuvable.");
        }

        // Récupérez les détails de facturation associés à l'utilisateur depuis la relation billingDetails
        BillingDetails billingDetails = user.getBillingDetails();
        if (billingDetails == null) {
            throw new IllegalArgumentException("Détails de facturation introuvables.");
        }

        // Vérifiez le format des informations de carte de crédit
        if (!isValidCardCode(cardCode) || !isValidSecurityCode(securityCode) || !isValidCardDate(cardDate)) {
            throw new IllegalArgumentException("Informations de carte de crédit invalides.");
        }

        // Marquez la commande comme validée (vous devrez également mettre à jour les billets pour les marquer comme réservés)
        order.setValidated(true);
        orderRepository.save(order);

        return true; // La commande a été validée avec succès
    }

    // Méthodes pour valider le format des informations de carte de crédit
    private boolean isValidCardCode(String cardCode) {
        return cardCode != null && cardCode.matches("\\d{16}"); // Doit contenir 16 chiffres
    }

    private boolean isValidSecurityCode(String securityCode) {
        return securityCode != null && securityCode.matches("\\d{3}"); // Doit contenir 3 chiffres
    }

    private boolean isValidCardDate(String cardDate) {
        return cardDate != null && cardDate.matches("\\d{2}/\\d{4}"); // Doit être au format mm/aaaa
    }
}
