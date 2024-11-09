package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
}

