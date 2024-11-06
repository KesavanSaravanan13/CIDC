package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seat, Long> {
}
