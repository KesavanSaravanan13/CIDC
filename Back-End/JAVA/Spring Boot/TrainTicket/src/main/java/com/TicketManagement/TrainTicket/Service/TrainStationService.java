package com.TicketManagement.TrainTicket.Service;

import com.TicketManagement.TrainTicket.Repository.PlaceRepository;
import com.TicketManagement.TrainTicket.Repository.TrainStationRepository;
import com.TicketManagement.TrainTicket.Table.PlaceDetails;
import com.TicketManagement.TrainTicket.Table.TrainStation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class TrainStationService {

    @Autowired
    private TrainStationRepository trainStationRepository;
    @Autowired
    private PlaceRepository placeRepository;

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

