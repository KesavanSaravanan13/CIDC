package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import com.TicketManagement.TrainTicket.repository.TrainStationRepository;
import com.TicketManagement.TrainTicket.entity.TrainStation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainStationService {

    private final TrainStationRepository trainStationRepository;

    public List<TrainStation> findAll() {
        return trainStationRepository.findAll();
    }

    public TrainStation findById(int id) {
        return trainStationRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        trainStationRepository.deleteById(id);
    }
}

