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
        ad.setCategory(adDTO.getCategory());
        ad.setCity(adDTO.getCity());
        return ad;
    }

    public boolean existsById(Long id) {
        return adRepository.existsById(id);
    }
    @Transactional
    public AdDTO createAdWithTickets(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setName(adDTO.getName());
        ad.setEventDate(adDTO.getEventDate());
        ad.setAdminAdCheck(adDTO.isAdminAdCheck());
        ad.setAdminComment(adDTO.getAdminComment());
        ad.setCategory(adDTO.getCategory());
        ad.setCity(adDTO.getCity());

        List<Ticket> tickets = new ArrayList<>();
        if (adDTO.getTickets() != null) {
            adDTO.getTickets().forEach(ticketDTO -> {
                Ticket ticket = new Ticket();
                ticket.setDescription(ticketDTO.getDescription());
                ticket.setTicketStatus(ticketDTO.isTicketStatus());
                ticket.setPrice(ticketDTO.getPrice());
                ticket.setAd(ad);
                tickets.add(ticket);
            });
        }

        ad.setTickets(tickets);
        Ad savedAd = adRepository.save(ad);
        return convertToDTO(savedAd);
    }

    private Ticket convertToTicketEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setPrice(ticketDTO.getPrice());

        if (ticketDTO.getAdId() != null) {
            Optional<Ad> optionalAd = adRepository.findById(ticketDTO.getAdId());
            if (optionalAd.isPresent()) {
                Ad ad = optionalAd.get();
                ticket.setAd(ad);
            } else {
                throw new EntityNotFoundException("Ad not found with ID: " + ticketDTO.getAdId());
            }
        }

        return ticket;
    }


private AdDTO convertToDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        adDTO.setName(ad.getName());
        //adDTO.setTickets(ad.getTickets());
        adDTO.setCategory(ad.getCategory());
        adDTO.setCity(ad.getCity());
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


    public AdDTO getAdById(Long id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Annonce non trouvée"));


        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        adDTO.setCategory(ad.getCategory());
        adDTO.setName(ad.getName());
        adDTO.setCity(ad.getCity());
        adDTO.setEventDate(ad.getEventDate());


        return adDTO;
    }

   public List<Ad> findAdsByEventDate(LocalDate eventDate) {
       return adRepository.findAdsByEventDate(eventDate);
   }

}




