package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ad;
import com.appfilrouge.projetfilrouge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    User findBySellerId(Long id);

    List<Ad> findByNameContaining(String name);

    List<Ad> findAdsByEventDate(LocalDate eventDate);

    List<Ad> findAdsByMail(String mail);

    List<Ad> findByNameContainingOrEventDate(String name, LocalDate eventDate);
}