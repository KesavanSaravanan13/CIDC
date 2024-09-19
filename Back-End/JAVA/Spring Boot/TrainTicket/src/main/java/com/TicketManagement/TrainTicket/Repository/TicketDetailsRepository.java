package com.TicketManagement.TrainTicket.Repository;

import com.TicketManagement.TrainTicket.Table.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDetailsRepository extends JpaRepository<TicketDetails,Integer> {
}
