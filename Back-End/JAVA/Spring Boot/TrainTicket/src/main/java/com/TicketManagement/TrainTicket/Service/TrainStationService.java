package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.entity.TrainStation;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import com.TicketManagement.TrainTicket.repository.TrainStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainStationService {

    private final PlaceRepository PlaceRepo;
    private final TrainStationRepository trainStationRepo;

    public List<TrainStationDTO> getTrainDetails() {
        List<TrainStationDTO> trainStationList = new ArrayList<>();
        this.trainStationRepo.findAll().forEach(trainStation -> {
            if (trainStation.getStatus()) {
                trainStationList.add(new TrainStationDTO(trainStation.getStationId(), trainStation.getStationName(), trainStation.getPlace(), trainStation.getStatus()));
            }
        });
        return trainStationList;
    }

    public void saveTrainStation(final TrainStationDTO trainStationDTO) {
        getTrainDetails();
        TrainStation trainStation = new TrainStation();
        trainStation.setStationId(trainStationDTO.getStationId());
        trainStation.setStationName(trainStationDTO.getStationName());
        trainStation.setStatus(trainStationDTO.getStatus());
        trainStation.setPlace(this.PlaceRepo.findById(trainStationDTO.getPlace().getPlaceId()).orElseThrow(() ->
            new RuntimeException("No Place Found")
        ));
        this.trainStationRepo.save(trainStation);
    }

    public TrainStationDTO getTrainStationById(final Long id) {
        TrainStation trainStation = this.trainStationRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
        return new TrainStationDTO(trainStation.getStationId(),trainStation.getStationName(),trainStation.getPlace(),trainStation.getStatus());
    }

    public List<TrainStationDTO> getAllTrainStations() {
        return new ArrayList<>(getTrainDetails());
    }

    public String deleteTrainStation(final Long stationId) {
        final String[] str = {""};
        str[0] = "No Id Matched";
        getTrainDetails().forEach(user -> {
            if (user.getStationId().equals(stationId)) {
                user.setStatus(false);
                saveTrainStation(user);
                str[0] = "Deleted";
            }
        });
        return str[0];
    }
}

