package com.appfilrouge.projetfilrouge.controllers;


import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.services.AdService;
import com.appfilrouge.projetfilrouge.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    AdService adservice;

    @PostMapping("/createdAd")
    public void createAd(@RequestBody Ad newAd, @AuthenticationPrincipal UserDetails userDetails) {
        adservice.createAd(newAd,userDetails);
    }

    @GetMapping("/all")
    public List<Ad> getAll() {
        return adservice.getAll();
    }
}




