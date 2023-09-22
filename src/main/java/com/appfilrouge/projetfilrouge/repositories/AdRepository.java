package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.AdAddress;
import com.appfilrouge.projetfilrouge.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> , JpaSpecificationExecutor<Ad> {

    List<Ad> findByOnlineAdStatus(boolean onlineAdStatus);
    List<Ad> findAdByEventDate(LocalDate eventDate);
    List<Ad> findAdByCategory(Category category);
    List<Ad> findByCategoryOrAdAddressOrNameContaining(Category category, Optional<AdAddress> adAddress, String name);
    List<Ad> findByCategory_AdStyleOrCategory_AdTypeOrNameContainingOrAdAddress_StreetAndAdAddress_City(
            String adStyle, String adType, String name,String numberStreet,String city);

}
