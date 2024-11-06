package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}