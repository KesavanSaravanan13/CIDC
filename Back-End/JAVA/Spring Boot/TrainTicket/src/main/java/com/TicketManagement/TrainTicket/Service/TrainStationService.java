package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.entity.TrainStation;
import com.TicketManagement.TrainTicket.repository.TrainStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainStationService {

    private final TrainStationRepository trainStationRepo;
    private List<TrainStationDTO> trainStationList;

    public void toDTO() {
        trainStationList = new ArrayList<>();
        for (TrainStation trainStation : trainStationRepo.findAll()) {
            if (trainStation.getStatus() != null && trainStation.getStatus().equalsIgnoreCase("Available")) {
                trainStationList.add(new TrainStationDTO(trainStation.getStationId(), trainStation.getStationName(), trainStation.getPlace(), trainStation.getStatus()));
            }
        }
    }

    public void toEntity(final TrainStationDTO trainStationDTO) {
        TrainStation trainStation = new TrainStation();
        trainStation.setStationId(trainStationDTO.getStationId());
        trainStation.setStationName(trainStationDTO.getStationName());

        Place place = new Place();
        place.setPlaceId(trainStationDTO.getPlace().getPlaceId());
        trainStation.setPlace(place);
        trainStationRepo.save(trainStation);
    }

    public void saveTrainStation(final TrainStationDTO trainStation) {
        toDTO();
        new TrainStationDTO(trainStation.getStationId(), trainStation.getStationName(), trainStation.getPlace(), trainStation.getStatus());
    }

    public TrainStationDTO getTrainStationById(final Long id) {
        toDTO();
        for (TrainStationDTO TrainStationDTO : trainStationList) {
            if (TrainStationDTO.getStationId().equals(id)) {
                return TrainStationDTO;
            }
        }
        return null;
    }

    public List<TrainStationDTO> getAllTrainStations() {
        toDTO();
        return new ArrayList<>(trainStationList);
    }

    public String deleteTrainStation(final Long stationId) {
        final String[] str = {""};
        toDTO();
        trainStationList.forEach(user -> {
            if (user.getStationId().equals(stationId)) {
                user.setStatus("Not-Available");
                toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}

