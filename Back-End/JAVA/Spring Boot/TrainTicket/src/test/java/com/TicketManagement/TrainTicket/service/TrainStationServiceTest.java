package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.entity.TrainStation;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import com.TicketManagement.TrainTicket.repository.TrainStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrainStationServiceTest {

    @InjectMocks
    private TrainStationService trainStationService;

    @Mock
    private PlaceRepository placeRepo;

    @Mock
    private TrainStationRepository trainStationRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTrainDetails_ShouldReturnListOfActiveTrainStations() {
        TrainStation trainStation1 = new TrainStation(1L, "Station A", new Place(), true);
        TrainStation trainStation2 = new TrainStation(2L, "Station B", new Place(), false);
        when(this.trainStationRepo.findAll()).thenReturn(Arrays.asList(trainStation1, trainStation2));

        List<TrainStationDTO> result = this.trainStationService.getTrainDetails();

        assertEquals(1, result.size());
        assertEquals("Station A", result.get(0).getStationName());
    }

    @Test
    void saveTrainStation_ShouldSaveTrainStation() {
        Place place = new Place(1L, "Place A", 5, true);
        TrainStationDTO trainStationDTO = new TrainStationDTO(1L, "Station A", place, true);
        when(this.placeRepo.findById(any(Long.class))).thenReturn(Optional.of(place));

        this.trainStationService.saveTrainStation(trainStationDTO);

        verify(this.trainStationRepo, times(1)).save(any(TrainStation.class));
    }

    @Test
    void getTrainStationById_ShouldReturnTrainStationDTO() {
        TrainStation trainStation = new TrainStation(1L, "Station A", new Place(), true);
        when(this.trainStationRepo.findById(1L)).thenReturn(Optional.of(trainStation));

        TrainStationDTO result = this.trainStationService.getTrainStationById(1L);

        assertEquals("Station A", result.getStationName());
    }

    @Test
    void getTrainStationById_ShouldThrowExceptionWhenNotFound() {
        when(this.trainStationRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.trainStationService.getTrainStationById(1L);
        });

        assertEquals("Not Found", exception.getMessage());
    }

    @Test
    void deleteTrainStation_ShouldReturnDeletedMessage() {
        Place place = new Place(1L, "Place A", 5, true); // Create a Place instance
        TrainStation trainStation = new TrainStation(1L, "Station A", place, true); // Create a TrainStation instance

        when(this.trainStationRepo.findById(1L)).thenReturn(Optional.of(trainStation));

        String result = this.trainStationService.deleteTrainStation(1L);

        assertEquals("Deleted", result);
        assertFalse(trainStation.getStatus());
        verify(this.trainStationRepo, times(1)).save(trainStation);
    }

    @Test
    void deleteTrainStation_ShouldReturnNoIdMatchedMessage() {
        when(this.trainStationRepo.findById(1L)).thenReturn(Optional.empty());

        String result = this.trainStationService.deleteTrainStation(1L);

        assertEquals("No Id Matched", result);
        verify(this.trainStationRepo, never()).save(any());
    }

    @Test
    void getAllTrainStation_ShouldReturnListOfTrains() {
        TrainStation ticketDetails1 = new TrainStation();
        ticketDetails1.setStationId(1L);
        ticketDetails1.setStatus(true);

        TrainStation ticketDetails2 = new TrainStation();
        ticketDetails2.setStationId(2L);
        ticketDetails2.setStatus(true);

        when(this.trainStationRepo.findAll()).thenReturn(Arrays.asList(ticketDetails1, ticketDetails2));

        List<TrainStationDTO> result = this.trainStationService.getAllTrainStations();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getStationId());
        assertEquals(2L, result.get(1).getStationId());
    }

}
