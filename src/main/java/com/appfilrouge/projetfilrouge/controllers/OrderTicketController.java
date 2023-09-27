package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.entities.OrderTicket;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.services.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderTicketController {

    @Autowired
    private OrderTicketService orderTicketService;

    // Endpoint pour récupérer une commande par ID
    @GetMapping("/{id}")
    public Optional<OrderTicket> getOrderById(@PathVariable Long id) {
       return orderTicketService.getOrderById(id);
    }

    @PostMapping("/validatesimple/{id}")
    public void validateOrderSimple(@PathVariable Long id,@RequestBody String card) {
        orderTicketService.validateOrderSimple(id, card);
    }

    @GetMapping("/byticket/{ticketId}")
    public OrderTicket findOrderTicketsByTicketId(@PathVariable Long ticketId) {
        return orderTicketService.findOrderTicketsByTicketId(ticketId);
    }

    @GetMapping("/seller/{sellerId}")
    public OrderTicket findOrderbySellerId(@PathVariable Long sellerId) {
        return orderTicketService.findOrderbySellerId(sellerId);
    }

    // Endpoint pour créer une commande de tickets
    @PostMapping("/creation")
    public ResponseEntity<OrderTicket> createOrder(@RequestBody OrderTicket orderTicket) {
        OrderTicket createdOrder = orderTicketService.createOrder(orderTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PostMapping("/{id}/validation")
    public ResponseEntity<String> validateOrder(@PathVariable Long id, @RequestBody User user, @RequestBody Map<String, String> paymentInfo) {
        try {
            String cardCode = paymentInfo.get("cardCode");
            String securityCode = paymentInfo.get("securityCode");
            String cardDate = paymentInfo.get("cardDate");

            boolean isValidated = orderTicketService.validateOrder(id, user, cardCode, securityCode, cardDate);

            if (isValidated) {
                return ResponseEntity.ok("La commande a été validée avec succès.");
            } else {
                return ResponseEntity.badRequest().body("Échec de la validation de la commande.");
            }
        } catch (IllegalArgumentException e) {
            // Gérez les exceptions appropriées et renvoyez une réponse d'erreur
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}






