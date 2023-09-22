package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.entities.Seller;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BuyerService buyerService;
    private final SellerService sellerService;

    @Autowired
    public UserService(UserRepository userRepository, BuyerService buyerService, SellerService sellerService) {
        this.userRepository = userRepository;
        this.buyerService = buyerService;
        this.sellerService = sellerService;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);

        if (user.getBuyer() != null) {
            Buyer buyer = new Buyer(user, new ArrayList<>());
            buyerService.createBuyer(buyer);
        }

        if (user.getSeller() != null) {
            Seller seller = new Seller(user, new ArrayList<>());
            sellerService.createSeller(seller);
        }
    }
}