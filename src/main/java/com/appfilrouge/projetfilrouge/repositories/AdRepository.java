package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> , JpaSpecificationExecutor<Ad> {


    List<Ad> findAdByEventDate(LocalDate eventDate);

    List<Ad> findByNameContaining(String name);


}
