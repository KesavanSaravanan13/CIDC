package com.TicketManagement.TrainTicket.Service;

import com.TicketManagement.TrainTicket.Repository.TrainStationRepository;
import com.TicketManagement.TrainTicket.Table.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainStationService {

    @Autowired
    private TrainStationRepository trainStationRepository;

    public List<TrainStation> findAll() {
        return trainStationRepository.findAll();
    }

    public TrainStation save(TrainStation trainStation) {
        return trainStationRepository.save(trainStation);
    }

    public TrainStation findById(int id) {
        return trainStationRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        trainStationRepository.deleteById(id);
    }
}

