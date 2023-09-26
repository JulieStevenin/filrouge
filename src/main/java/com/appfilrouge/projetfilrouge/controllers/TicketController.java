package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.TicketDTO;

import com.appfilrouge.projetfilrouge.services.AdService;
import com.appfilrouge.projetfilrouge.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AdService adService;

    @PostMapping("/new")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO createdTicket = ticketService.createTicket(ticketDTO);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        if (!ticketService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TicketDTO updatedTicket = ticketService.updateTicket(id, ticketDTO);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (!ticketService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> allTickets = ticketService.getAllTickets();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        TicketDTO ticketDTO = ticketService.getTicketById(id);
        if (ticketDTO != null) {
            return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-ad/{adId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByAd(@PathVariable Long adId) {
        List<TicketDTO> ticketsByAd = ticketService.getTicketsByAd(adId);
        return new ResponseEntity<>(ticketsByAd, HttpStatus.OK);
    }

}
