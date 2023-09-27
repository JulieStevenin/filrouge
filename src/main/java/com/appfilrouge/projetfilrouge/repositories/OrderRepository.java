package com.appfilrouge.projetfilrouge.repositories;

import com.appfilrouge.projetfilrouge.entities.OrderTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends JpaRepository<OrderTicket, Long> {

    OrderTicket findOrderTicketBySellerId(Long id);


    OrderTicket findOrderTicketsById(Long id);

    @Query("SELECT ot FROM OrderTicket ot WHERE ot.id = (SELECT t.orderTicket.id FROM Ticket t WHERE t.id = :ticketId)")
    OrderTicket findOrderTicketsByTicketId(@Param("ticketId") Long ticketId);

}
