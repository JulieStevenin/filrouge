package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.Ticket;
import com.appfilrouge.projetfilrouge.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTicketStatus(boolean ticketStatus);

    List<Ticket> findByAd(Ad ad);

    List<Ticket> findByAd_Id(Long adId);
}