package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



import java.time.LocalDate;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    User findBySellerId(Long id);
    List<Ad> findByNameContaining(String name);
    List<Ad> findAdsByEventDate(LocalDate eventDate);

        List<Ad> findAdsByMail (String mail);




}
