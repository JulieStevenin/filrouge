package com.appfilrouge.projetfilrouge.services;



import com.appfilrouge.projetfilrouge.dto.AdDTO;
import com.appfilrouge.projetfilrouge.dto.TicketDTO;
import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public AdDTO createAdd(AdDTO adDTO) {
        Ad ad = convertToEntity(adDTO);
        Ad savedAd = adRepository.save(ad);
        return convertToDTO(savedAd);
    }
   /* public Ad createAd(AdDTO adDTO) {
        Ad ad = new Ad();
        List<Ticket> tickets = new ArrayList<>();
        for (TicketDTO ticketDTO : adDTO.getTickets()) {
            Ticket ticket = new Ticket();

            ticket.setAd(ad);
            tickets.add(ticket);
        }
        ad.setTickets(tickets);


        return adRepository.save(ad);
    }*/
    @Transactional
    public AdDTO updateAd(Long id, AdDTO adDTO) {
        Optional<Ad> optionalAd = adRepository.findById(id);

        if (optionalAd.isPresent()) {
            Ad existingAd = optionalAd.get();
            existingAd.setName(adDTO.getName());
            existingAd.setEventDate(adDTO.getEventDate());
            existingAd.setAdminAdCheck(adDTO.isAdminAdCheck());
            existingAd.setAdminComment(adDTO.getAdminComment());
            Ad updatedAd = adRepository.save(existingAd);

            return convertToDTO(updatedAd);
        } else {
            throw new EntityNotFoundException("Ad not found with id: " + id);
        }
    }

  /*  @Transactional
    public AdDTO addTicketToAd(Long id, TicketDTO ticketDTO) {
        Optional<Ad> optionalAd = adRepository.findById(id);

        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            Ticket ticket = convertTicketDTOToEntity(ticketDTO);
            ticket.setAd(ad);
            ad.addTicket(ticket);
            Ticket savedTicket = ticketRepository.save(ticket);
            return convertToDTO(ad);
        } else {
            throw new EntityNotFoundException("Ad not found with id: " + id);
        }
    }*/

    public List<AdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return ads.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }


    private Ticket convertTicketDTOToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketStatus(ticketDTO.isTicketStatus());


        return ticket;
    }

    private Ad convertToEntity(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setName(adDTO.getName());
        ad.setEventDate(adDTO.getEventDate());
        ad.setAdminAdCheck(adDTO.isAdminAdCheck());
        ad.setAdminComment(adDTO.getAdminComment());


        return ad;
    }

    public boolean existsById(Long id) {
        return adRepository.existsById(id);
    }

    private AdDTO convertToDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        adDTO.setName(ad.getName());

        adDTO.setEventDate(ad.getEventDate());
        adDTO.setAdminAdCheck(ad.isAdminAdCheck());
        adDTO.setAdminComment(ad.getAdminComment());
        if (ad.getSeller() != null) {
            adDTO.setSellerId(ad.getSeller().getId());
        }

        return adDTO;
    }


    private TicketDTO convertTicketToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setTicketStatus(ticket.isTicketStatus());


        return ticketDTO;
    }

    public List<Ad> findAdsByName(String name) {
        return adRepository.findByNameContaining(name);

    }
    public List<Ad> findAdsByEventDate(LocalDate eventDate) {
        return adRepository.findAdByEventDate(eventDate);
    }
}




