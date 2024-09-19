package com.TicketManagement.TrainTicket.Repository;

import com.TicketManagement.TrainTicket.Table.PlaceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceDetails, String> {
}

