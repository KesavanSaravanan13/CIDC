package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.mapper.TrainStationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainStationService {

    private final TrainStationMapper trainMapper;
    private final List<TrainStationDTO> trainStationList = new ArrayList<>();

    public void saveTrainStation(TrainStationDTO trainStation) {
        trainMapper.toDTO();
        new TrainStationDTO(trainStation.getStationId(),trainStation.getStationName(),trainStation.getPlace());
    }

    public TrainStationDTO getTrainStationById(Long id) {
        trainMapper.toDTO();
        for (TrainStationDTO TrainStationDTO : trainMapper.getTrainStationList()) {
            if (TrainStationDTO.getStationId().equals(id)) {
                return TrainStationDTO;
            }
        }
        return null;
    }

    public List<TrainStationDTO> getAllTrainStations() {
        trainMapper.toDTO();
        return new ArrayList<>(trainMapper.getTrainStationList());
    }

    public void deleteTrainStation(Long stationId) {
        trainMapper.toDTO();
        trainMapper.getTrainStationList().removeIf(TrainStationDTO -> TrainStationDTO.getStationId().equals(stationId));
    }
}

