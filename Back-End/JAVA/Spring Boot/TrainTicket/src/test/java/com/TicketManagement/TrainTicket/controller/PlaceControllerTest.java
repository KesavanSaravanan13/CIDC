package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.service.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaceControllerTest {

    @Mock
    private PlaceService placeService;

    @InjectMocks
    private PlaceController placeController;

    List<PlaceDTO> placeDTOList = new ArrayList<>(Arrays.asList(
            new PlaceDTO(1L, "Springfield", 2,true),
            new PlaceDTO(2L, "Shelbyville", 3,false),
            new PlaceDTO(3L, "Ogdenville", 4,true)
    ));

//    @BeforeEach
//    public void setup() {
//        // Any setup actions like token verification can be added here
//    }

    @Test
    public void createPlaceUT() {
        PlaceDTO place = new PlaceDTO(1L, "Springfield", 2,true);
        placeController.createPlace(place);
        verify(placeService, times(1)).savePlace(place);
    }

    @Test
    public void getAllPlacesUT() {
        when(placeService.getAllPlaces()).thenReturn(placeDTOList);

        List<PlaceDTO> result = placeController.getAllPlaces();

        assertThat(result).isEqualTo(placeDTOList);
        verify(placeService, times(1)).getAllPlaces();
    }

    @Test
    public void getPlaceByIdUT() {
        Long placeId = 1L;
        PlaceDTO placeDTO = new PlaceDTO(1L, "Springfield", 2,true);

        when(placeService.getPlaceById(placeId)).thenReturn(placeDTO);

        PlaceDTO result = placeController.getPlaceByName(placeId);

        assertThat(result).isEqualTo(placeDTO);
        verify(placeService, times(1)).getPlaceById(placeId);
    }

    @Test
    public void deletePlaceUT() {
        Long placeId = 1L;

        when(placeService.deletePlace(placeId)).thenReturn("Deleted");

        String result = placeController.deletePlace(placeId);

        assertThat(result).isEqualTo("Deleted");
        verify(placeService, times(1)).deletePlace(placeId);
    }
}