package com.appfilrouge.projetfilrouge.services;

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
        User adduser = new User(user.getFname(), user.getLname(), user.getMail(), user.getPassword(), user.getPhoto(), user.getBuyer(), user.getSeller(), user.getBillingDetails());
        usercrud.save(user);
        return adduser;
    }

    public List<User> getAllUser() {
        return usercrud.findAll();
    }
}
