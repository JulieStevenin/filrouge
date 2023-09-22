package com.appfilrouge.projetfilrouge.services;



import com.appfilrouge.projetfilrouge.dto.AdDTO;
import com.appfilrouge.projetfilrouge.dto.TicketDTO;
import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.AdAddress;
import com.appfilrouge.projetfilrouge.entities.Category;
import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdAddressRepository adAddressRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public AdDTO createAd(AdDTO adDTO) {
        Ad ad = convertToEntity(adDTO);
        Ad savedAd = adRepository.save(ad);
        return convertToDTO(savedAd);
    }

    @Transactional
    public AdDTO updateAd(Long id, AdDTO adDTO) {
        Optional<Ad> optionalAd = adRepository.findById(id);

        if (optionalAd.isPresent()) {
            Ad existingAd = optionalAd.get();
            // Mettez à jour les champs de l'annonce existante avec les valeurs de adDTO.
            existingAd.setName(adDTO.getName());
            existingAd.setDocument(adDTO.getDocument());
            existingAd.setNumberTickets(adDTO.getNumberTickets());
            existingAd.setEventDate(adDTO.getEventDate());
            existingAd.setAdminAdCheck(adDTO.isAdminAdCheck());
            existingAd.setAdminComment(adDTO.getAdminComment());
            existingAd.setOnlineAdStatus(adDTO.isOnlineAdStatus());

            // Récupérez la catégorie à partir de l'ID de la catégorie et définissez la relation.
            Category category = categoryRepository.findById(adDTO.getCategoryId()).orElse(null);
            existingAd.setCategory(category);

            // Récupérez l'adresse de l'annonce à partir de l'ID de l'adresse de l'annonce et définissez la relation.
            AdAddress adAddress = adAddressRepository.findById(adDTO.getAdAddressId()).orElse(null);
            existingAd.setAdAddress(adAddress);

            // Vous pouvez également gérer la relation avec les tickets et le vendeur ici.

            // Enregistrez les modifications dans la base de données.
            Ad updatedAd = adRepository.save(existingAd);

            return convertToDTO(updatedAd);
        } else {
            throw new EntityNotFoundException("Ad not found with id: " + id);
        }
    }

    @Transactional
    public AdDTO addTicketToAd(Long id, TicketDTO ticketDTO) {
        Optional<Ad> optionalAd = adRepository.findById(id);

        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            Ticket ticket = convertTicketDTOToEntity(ticketDTO);


            ticket.setAd(ad);
            ad.addTicket(ticket);

            // Enregistrez le ticket dans la base de données.
            Ticket savedTicket = ticketRepository.save(ticket);

            // Mettez à jour l'état en ligne de l'annonce si nécessaire.
            ad.updateOnlineStatus();
            adRepository.save(ad);

            return convertToDTO(ad);
        } else {
            throw new EntityNotFoundException("Ad not found with id: " + id);
        }
    }

    // Méthode pour récupérer toutes les annonces.
    public List<AdDTO> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return ads.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Méthode pour supprimer une annonce par ID.
    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }

    // Méthodes de conversion vers des entités pour les autres entités (Category, AdAddress, Seller, Ticket).

    // Méthodes de conversion vers des DTO pour les autres entités (Category, AdAddress, Seller, Ticket).

    // Autres méthodes du service.

    private Ticket convertTicketDTOToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketStatus(ticketDTO.isTicketStatus());
        // Convertissez d'autres champs du ticket ici si nécessaire.

        return ticket;
    }

    private Ad convertToEntity(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setName(adDTO.getName());
        ad.setDocument(adDTO.getDocument());
        ad.setNumberTickets(adDTO.getNumberTickets());
        ad.setEventDate(adDTO.getEventDate());
        ad.setAdminAdCheck(adDTO.isAdminAdCheck());
        ad.setAdminComment(adDTO.getAdminComment());
        ad.setOnlineAdStatus(adDTO.isOnlineAdStatus());
        // Vous pouvez également convertir d'autres champs ici.

        return ad;
    }
    public boolean existsById(Long id) {
        return adRepository.existsById(id);
    }
    private AdDTO convertToDTO(Ad ad) {
        AdDTO adDTO = new AdDTO();
        adDTO.setId(ad.getId());
        adDTO.setName(ad.getName());
        adDTO.setDocument(ad.getDocument());
        adDTO.setNumberTickets(ad.getNumberTickets());
        adDTO.setEventDate(ad.getEventDate());
        adDTO.setAdminAdCheck(ad.isAdminAdCheck());
        adDTO.setAdminComment(ad.getAdminComment());
        adDTO.setOnlineAdStatus(ad.isOnlineAdStatus());

        if (ad.getCategory() != null) {
            adDTO.setCategoryId(ad.getCategory().getId());
        }

        if (ad.getAdAddress() != null) {
            adDTO.setAdAddressId(ad.getAdAddress().getId());
        }

        // Récupérez les tickets sous forme de DTOs.
       /* if (ad.getTickets() != null) {
            adDTO.setTickets(ad.getTickets().stream().map(this::convertTicketToDTO).collect(Collectors.toList()));
        }*/

        // Récupérez l'ID du vendeur depuis l'entité Seller.
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

}




