package com.appfilrouge.projetfilrouge.controllers;


import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    AdService adService;

    @PostMapping("/createdAd")
    public void createAd(@RequestBody Ad newAd, @AuthenticationPrincipal UserDetails userDetails) {
        adService.createAd(newAd, userDetails);
    }

    @GetMapping("/all")
    public List<Ad> getAll() {
        return adService.getAll();
    }

    @GetMapping("user/{mail}")
    public List<Ad> findAdByMail(@PathVariable String mail) {
        return adService.findAdByMail(mail);
    }

    @GetMapping("/search")
    public List<Ad> searchAds(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String date) {
        if (name != null && date != null && !date.trim().isEmpty()) {
            return adService.searchAds(name, LocalDate.parse(date));
        } else if (name != null) {
            return adService.searchAdsByName(name);
        } else if (date != null) {
            return adService.searchAdsByDate(LocalDate.parse(date));
        } else {
            throw new IllegalArgumentException("Au moins un critère de recherche (name ou date) doit être fourni.");
        }
    }
}