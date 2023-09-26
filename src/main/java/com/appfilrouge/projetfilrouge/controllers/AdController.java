package com.appfilrouge.projetfilrouge.controllers;


import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    AdService adservice;

    @PostMapping("/createdAd")
    public void createAd(@RequestBody Ad newAd ) {
        adservice.createAd(newAd);
    }

    @GetMapping("/all")
    public List<Ad> getAll(){
      return adservice.getAll();
    }
}




