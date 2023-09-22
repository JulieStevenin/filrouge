package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    //Visiteurs
    List<Ad> findAdsByTicketsIsNotEmptyAndAdminAdCheckTrue();

    //Users

}
