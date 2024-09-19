package com.TicketManagement.TrainTicket.Repository;

import com.TicketManagement.TrainTicket.Table.TrainStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainStationRepository extends JpaRepository<TrainStation,Integer> {
}
