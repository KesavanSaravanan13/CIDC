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
        new TrainStationDTO(trainStation.getStationId(), trainStation.getStationName(), trainStation.getPlace(), trainStation.getStatus());
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

    public String deleteTrainStation(Long stationId) {
        final String[] str = {""};
        trainMapper.toDTO();
        trainMapper.getTrainStationList().forEach(user -> {
            if (user.getStationId().equals(stationId)) {
                user.setStatus("Not-Available");
                trainMapper.toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}

