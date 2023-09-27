package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer getBuyerById(Long id) {
        Optional<Buyer> optionalBuyer = buyerRepository.findById(id);
        if (optionalBuyer.isPresent()) {
            return optionalBuyer.get();
        } else {
            return null;
        }
    }

    public Buyer createBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }
}