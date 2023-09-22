package com.appfilrouge.projetfilrouge.controllers;

import com.appfilrouge.projetfilrouge.dto.AdDTO;

import com.appfilrouge.projetfilrouge.repositories.AdAddressRepository;
import com.appfilrouge.projetfilrouge.repositories.SellerRepository;
import com.appfilrouge.projetfilrouge.services.AdService;
import com.appfilrouge.projetfilrouge.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController

@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdService adService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private AdAddressRepository adAddressRepository;

    @PostMapping("/new")
    public ResponseEntity<AdDTO> createAd(@RequestBody AdDTO adDTO) {
        AdDTO createdAd = adService.createAd(adDTO);
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
        // Assurez-vous que l'annonce avec l'ID donné existe avant de la supprimer.
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
}

