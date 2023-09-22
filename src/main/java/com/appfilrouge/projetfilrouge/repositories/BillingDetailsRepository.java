package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {

    BillingDetails findByUserId(Long userId);
}
