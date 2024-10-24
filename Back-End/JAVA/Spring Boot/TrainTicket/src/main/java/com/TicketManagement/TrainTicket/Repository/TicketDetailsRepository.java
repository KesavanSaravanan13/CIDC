package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDetailsRepository extends JpaRepository<TicketDetails, Long> {
}
