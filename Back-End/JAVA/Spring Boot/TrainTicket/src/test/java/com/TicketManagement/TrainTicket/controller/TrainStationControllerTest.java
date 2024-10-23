package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.service.TrainStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainStationControllerTest {

    @Mock
    private TrainStationService trainStationService;

    @InjectMocks
    private TrainStationController trainStationController;

    private List<TrainStationDTO> trainStationList;
    private TrainStationDTO trainStation;

    @BeforeEach
    public void setUp() {
        trainStationList = Arrays.asList(
                new TrainStationDTO(1L, "Station 1", new Place(), true),
                new TrainStationDTO(2L, "Station 2", new Place(), true)
        );
        trainStation = new TrainStationDTO(3L, "Station 3", new Place(), true);
    }

    @Test
    public void getAllTrainStationsUT() {
        when(trainStationService.getAllTrainStations()).thenReturn(trainStationList);

        List<TrainStationDTO> result = trainStationController.getAllTrainStations();

        assertThat(result).isEqualTo(trainStationList);
        verify(trainStationService, times(1)).getAllTrainStations();
    }

    @Test
    public void getTrainStationByIdUT() {
        long stationId = 1L;
        TrainStationDTO expectedStation = new TrainStationDTO(stationId, "Station 1", new Place(), true);
        when(trainStationService.getTrainStationById(stationId)).thenReturn(expectedStation);

        TrainStationDTO result = trainStationController.getTrainStationById(stationId);

        assertThat(result).isEqualTo(expectedStation);
        verify(trainStationService, times(1)).getTrainStationById(stationId);
    }

    @Test
    public void saveTrainStationUT() {
        trainStationController.getTrainStationById(trainStation);

        verify(trainStationService, times(1)).saveTrainStation(trainStation);
    }

    @Test
    public void deleteTrainStationUT() {
        long stationId = 1L;
        when(trainStationService.deleteTrainStation(stationId)).thenReturn("Deleted");

        String result = trainStationController.deleteTrainStation(stationId);

        assertThat(result).isEqualTo("Deleted");
        verify(trainStationService, times(1)).deleteTrainStation(stationId);
    }
}
