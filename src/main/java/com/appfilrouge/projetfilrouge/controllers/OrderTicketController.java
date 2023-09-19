package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.entities.OrderTicket;
import com.appfilrouge.projetfilrouge.repositories.OrderRepository;
import com.appfilrouge.projetfilrouge.services.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderTicketController {

    @Autowired
    private OrderTicketService orderTicketService;

    // Endpoint pour récupérer une commande par ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderTicket> getOrderById(@PathVariable Long id) {
        OrderTicket order = orderTicketService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
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
    public ResponseEntity<String> generateInvoice(@PathVariable Long id) {
        String invoice = orderTicketService.createInvoice(id);
        return ResponseEntity.ok(invoice);
    }

}