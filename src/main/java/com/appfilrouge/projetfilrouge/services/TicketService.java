package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.dto.TicketDTO;
import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.OrderTicket;
import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.repositories.AdRepository;
import com.appfilrouge.projetfilrouge.repositories.OrderRepository;
import com.appfilrouge.projetfilrouge.repositories.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    AdRepository adRepository;
    @Autowired
    OrderRepository orderRepository;

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDTO(savedTicket);
    }



    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();

            existingTicket.setDescription(ticketDTO.getDescription());
            existingTicket.setTicketStatus(ticketDTO.isTicketStatus());
            existingTicket.setPrice(ticketDTO.getPrice());

            Ad ad = adRepository.findById(ticketDTO.getAdId()).orElse(null);
            existingTicket.setAd(ad);

            Ticket updatedTicket = ticketRepository.save(existingTicket);

            return convertToDTO(updatedTicket);
        } else {
            throw new EntityNotFoundException("Ticket not found with id: " + id);
        }
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TicketDTO getTicketById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            return convertToDTO(ticket);
        } else {
            return null;
        }
    }

    public boolean existsById(Long id) {
        return adRepository.existsById(id);
    }

    public List<TicketDTO> getTicketsByAd(Long adId) {
        List<Ticket> tickets = ticketRepository.findByAd_Id(adId);
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setTicketStatus(ticketDTO.isTicketStatus());
        ticket.setPrice(ticketDTO.getPrice());

        if (ticketDTO.getOrderTicketId() != null) {
            Optional<OrderTicket> optionalOrderTicket = orderRepository.findById(ticketDTO.getOrderTicketId());
            if (optionalOrderTicket.isPresent()) {
                ticket.setOrderTicket(optionalOrderTicket.get());
            } else {
                throw new EntityNotFoundException("OrderTicket not found with id: " + ticketDTO.getOrderTicketId());
            }
        }

        if (ticketDTO.getAdId() != null) {
            Optional<Ad> optionalAd = adRepository.findById(ticketDTO.getAdId());
            if (optionalAd.isPresent()) {
                ticket.setAd(optionalAd.get());
            } else {
                throw new EntityNotFoundException("Ad not found with id: " + ticketDTO.getAdId());
            }
        }

        return ticket;
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setDescription(ticket.getDescription());
        ticketDTO.setTicketStatus(ticket.isTicketStatus());
        ticketDTO.setPrice(ticket.getPrice());
        if (ticket.getOrderTicket() != null) {
            ticketDTO.setOrderTicketId(ticket.getOrderTicket().getId());
        }

        if (ticket.getAd() != null) {
            ticketDTO.setAdId(ticket.getAd().getId());
        }

        return ticketDTO;
    }

    public List<Ticket> getTicketByStatus(boolean ticketStatus) {
        return ticketRepository.findByTicketStatus(ticketStatus);
    }

    public List<Ticket> getTicketsByStatus(boolean status) {
        return ticketRepository.findByTicketStatus(status);
    }

    public void addTicketToAdd(Long adId, Ticket ticket) {

        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Annonce non trouvée avec l'ID : " + adId));
        ticket.setAd(ad);
        adRepository.save(ad);
        ticketRepository.save(ticket);
    }
}