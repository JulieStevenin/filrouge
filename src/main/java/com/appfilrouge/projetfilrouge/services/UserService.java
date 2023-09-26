package com.appfilrouge.projetfilrouge.services;

import com.appfilrouge.projetfilrouge.entities.Buyer;
import com.appfilrouge.projetfilrouge.entities.Seller;
import com.appfilrouge.projetfilrouge.entities.User;
import com.appfilrouge.projetfilrouge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {


    @Autowired
    private final UserRepository usercrud;


    public UserService(UserRepository usercrud) {
        this.usercrud = usercrud;
    }

    public User addUserWithBuyerSeller(User user) {
        Buyer newBuyer = new Buyer();
        newBuyer.setUser(user);
        user.setBuyer(newBuyer);
        Seller newSeller = new Seller();
        newSeller.setUser(user);
        user.setSeller(newSeller);
        usercrud.save(user);
        return user;
    }

    public List<User> getAllUser() {
        return usercrud.findAll();
    }



}
