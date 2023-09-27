package com.appfilrouge.projetfilrouge.controllers;


import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("*")
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    AdService adservice;

    @PostMapping("/createdAd")
    public void createAd(@RequestBody Ad newAd, @AuthenticationPrincipal UserDetails userDetails) {
        adservice.createAd(newAd, userDetails);
    }

    @GetMapping("/all")
    public List<Ad> getAll() {
        return adservice.getAll();
    }


    @GetMapping("/user/{mail}")
    public List<Ad> findAdByMail(@PathVariable String mail) {
        return adservice.findAdByMail(mail);
    }

    @PutMapping("/{adId}")
    public ResponseEntity<Ad> updateAd(@PathVariable Long adId, @RequestBody Ad updatedAdRequest) {
        Ad updatedAd = adservice.updateAd(adId, updatedAdRequest);

        if (updatedAd == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedAd, HttpStatus.OK);
    }
    @DeleteMapping("/{adId}")
    public ResponseEntity<String> deleteAd(@PathVariable Long adId) {
        try {
            adservice.deleteAd(adId);
            return new ResponseEntity<>("Ad deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Ad not found", HttpStatus.NOT_FOUND);
        }
    }

}




