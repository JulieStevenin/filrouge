package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.AdDTO;

import com.appfilrouge.projetfilrouge.entities.Ad;

import com.appfilrouge.projetfilrouge.repositories.SellerRepository;
import com.appfilrouge.projetfilrouge.services.AdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@CrossOrigin("*")
@RestController

@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;


    @Autowired
    private SellerRepository sellerRepository;


    @PostMapping("/new")
    public ResponseEntity<AdDTO> createAd(@RequestBody AdDTO adDTO) {
        AdDTO createdAd = adService.createAdd(adDTO);
        return new ResponseEntity<>(createdAd, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable Long id, @RequestBody AdDTO adDTO) {

        if (!adService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdDTO updatedAd = adService.updateAd(id, adDTO);
        return new ResponseEntity<>(updatedAd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        if (!adService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        adService.deleteAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdDTO>> getAllAds() {
        List<AdDTO> allAds = adService.getAllAds();
        return new ResponseEntity<>(allAds, HttpStatus.OK);
    }

    @GetMapping("/byName")
    public ResponseEntity<List<Ad>> findAdsByName(@RequestParam String name) {
        List<Ad> ads = adService.findAdsByName(name);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
    @GetMapping("/byEventDate")
    public ResponseEntity<List<Ad>> getAdsByEventDate(@RequestParam("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate) {
        List<Ad> ads = adService.findAdsByEventDate(eventDate);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}

