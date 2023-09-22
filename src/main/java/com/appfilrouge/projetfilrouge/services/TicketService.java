package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.dto.TicketDTO;
import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.repositories.AdRepository;
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

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity(ticketDTO);
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDTO(savedTicket);
    }

    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            // Mettez à jour les champs du ticket existant avec les valeurs de ticketDTO.
            existingTicket.setDescription(ticketDTO.getDescription());
            existingTicket.setTicketStatus(ticketDTO.isTicketStatus());
            existingTicket.setPrice(ticketDTO.getPrice());

            // Récupérez la commande de ticket à partir de l'ID de la commande de ticket et définissez la relation.
           /* OrderTicket orderTicket = orderTicketRepository.findById(ticketDTO.getOrderTicketId()).orElse(null);
            existingTicket.setOrderTicket(orderTicket);*/

            // Récupérez l'annonce à partir de l'ID de l'annonce et définissez la relation.
            Ad ad = adRepository.findById(ticketDTO.getAdId()).orElse(null);
            existingTicket.setAd(ad);

            // Enregistrez les modifications dans la base de données.
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

    // Méthode de conversion vers l'entité Ticket
    private Ticket convertToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setTicketStatus(ticketDTO.isTicketStatus());
        ticket.setPrice(ticketDTO.getPrice());
        // Vous pouvez également convertir d'autres champs ici.

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

        ad.setOnlineAdStatus(true);

        adRepository.save(ad);
        ticketRepository.save(ticket);
    }


    public void removeTicketFromAd(Long adId, Long ticketId) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Annonce non trouvée avec l'ID : " + adId));


        ticketRepository.deleteById(ticketId);

        updateAdOnlineStatus(ad);
    }

    private void updateAdOnlineStatus(Ad ad) {

        int ticketCount = ad.getTickets().size();

        ad.setOnlineAdStatus(ticketCount > 0);
        adRepository.save(ad);
    }

    public void addTicketToAd(Long adId, Ticket ticket) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new EntityNotFoundException("Annonce non trouvée avec l'ID : " + adId));


        ticket.setAd(ad);
        ticketRepository.save(ticket);

        updateAdOnlineStatus(ad);
    }


}

