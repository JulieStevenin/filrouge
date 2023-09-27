package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.entities.OrderTicket;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.OrderRepository;
import com.appfilrouge.projetfilrouge.services.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/order")
public class OrderTicketController {

    @Autowired
    private OrderTicketService orderTicketService;

    // Endpoint pour récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderTicket> getOrderById(@PathVariable Long id) {
        OrderTicket orderticket = orderTicketService.getOrderById(id);
        if (orderticket != null) {
            return ResponseEntity.ok(orderticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour créer une commande de tickets
    @PostMapping("/creationorder")
    public ResponseEntity<OrderTicket> createOrder(@RequestBody OrderTicket orderTicket) {
        OrderTicket createdOrder = orderTicketService.createOrder(orderTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    //Endpoint pour générer une facture pour une commande précise
    @PostMapping("/{id}/creationinvoice")
    public ResponseEntity<Map<String, Object>> createInvoice(@PathVariable Long id) {
        OrderTicket orderTicket = orderTicketService.getOrderById(id); // Remplacez findById par la méthode appropriée pour récupérer l'OrderTicket par son ID
        if (orderTicket == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> invoice = orderTicketService.createInvoice(orderTicket);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/{id}/validation")
    public ResponseEntity<String> validateOrder(@PathVariable Long id, @RequestBody User user, @RequestBody Map<String, String> paymentInfo) {
        try {
            String cardCode = paymentInfo.get("cardCode");
            String securityCode = paymentInfo.get("securityCode");
            String cardDate = paymentInfo.get("cardDate");

            boolean isValidated = orderTicketService.validateOrder(id, user, cardCode, securityCode, cardDate);

            if (isValidated) {
                return ResponseEntity.ok("Paiement validé");
            } else {
                return ResponseEntity.badRequest().body("Échec du paiement");
            }

        } catch (IllegalArgumentException e) {
            // Gérez les exceptions appropriées et renvoyez une réponse d'erreur
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}






