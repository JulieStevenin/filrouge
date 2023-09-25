package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;



import java.time.LocalDate;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {



    List<Ad> findByNameContaining(String name);

    List<Ad> findAdsByTicketsIsNotEmptyAndAdminAdCheckTrue();
    List<Ad> findAnnoncesByEventDateBetween(LocalDate startDate, LocalDate endDate);
    List<Ad> findAdsByEventDate(LocalDate eventDate);
    List<Ad> findAdsBy(LocalDate eventDate);
    List<Ad> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword);


}
