package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.entities.Seller;
import com.appfilrouge.projetfilrouge.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller getSellerById(Long id) {
        Optional<Seller> optionalSeller = sellerRepository.findById(id);
        if (optionalSeller.isPresent()) {
            return optionalSeller.get();
        } else {
            return null;
        }
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
}