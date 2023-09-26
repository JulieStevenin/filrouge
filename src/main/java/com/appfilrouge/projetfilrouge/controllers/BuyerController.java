package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/displaybuyer/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        Buyer buyer = buyerService.getBuyerById(id);
        if (buyer != null) {
            return ResponseEntity.ok(buyer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/creationbuyer")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        Buyer createdBuyer = buyerService.createBuyer(buyer);
        return new ResponseEntity<>(createdBuyer, HttpStatus.CREATED);
    }
}