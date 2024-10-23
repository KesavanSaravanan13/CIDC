package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);
}

