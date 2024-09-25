package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation,Integer> {
}
