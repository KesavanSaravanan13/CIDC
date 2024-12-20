package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepo;

    @InjectMocks
    private PlaceService placeService;

    private List<Place> placeList;
    private List<PlaceDTO> placeDTOList;

    @BeforeEach
    public void setup() {
        placeList = Arrays.asList(
                new Place(1L, "Springfield", 2, true),
                new Place(2L, "Shelbyville", 3, true),
                new Place(3L, "Ogdenville", 4, false)
        );

        placeDTOList = Arrays.asList(
                new PlaceDTO(1L, "Springfield", 2, true),
                new PlaceDTO(2L, "Shelbyville", 3, true)
        );
    }

    @Test
    public void testGetPlaceDetails() {
        when(this.placeRepo.findAll()).thenReturn(this.placeList);

        List<PlaceDTO> result = this.placeService.getPlaceDetails();

        assertThat(result).hasSize(2); // Only active places should be returned
        assertThat(result.get(0).getPlaceName()).isEqualTo("Springfield");
        assertThat(result.get(1).getPlaceName()).isEqualTo("Shelbyville");

        verify(this.placeRepo, times(1)).findAll();
    }

    @Test
    public void testSavePlace() {
        PlaceDTO placeDTO = new PlaceDTO(1L, "Springfield", 2, true);

        when(this.placeRepo.save(any(Place.class))).thenReturn(null);

        this.placeService.savePlace(placeDTO);

        verify(this.placeRepo, times(1)).save(any(Place.class));
    }

    @Test
    public void testGetPlaceById() {
        Long placeId = 1L;
        Place place = new Place(1L, "Springfield", 2, true);

        when(this.placeRepo.findById(placeId)).thenReturn(Optional.of(place));

        PlaceDTO result = this.placeService.getPlaceById(placeId);

        assertThat(result.getPlaceName()).isEqualTo("Springfield");

        verify(this.placeRepo, times(1)).findById(placeId);
    }

    @Test
    public void testGetPlaceByIdNotFound() {
        Long placeId = 1L;

        when(this.placeRepo.findById(placeId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> this.placeService.getPlaceById(placeId));

        verify(this.placeRepo, times(1)).findById(placeId);
    }

    @Test
    public void testGetAllPlaces() {

        when(this.placeRepo.findAll()).thenReturn(this.placeList);

        List<PlaceDTO> result = this.placeService.getAllPlaces();

        assertThat(result).hasSize(2);
        verify(this.placeRepo, times(1)).findAll();
    }

    @Test
    public void testDeletePlace() {
        Long placeId = 1L;
        PlaceDTO placeDTO = new PlaceDTO(1L, "Springfield", 2, true);

        when(this.placeRepo.findAll()).thenReturn(this.placeList);

        String result = this.placeService.deletePlace(placeId);

        assertThat(result).isEqualTo("Deleted");

        verify(this.placeRepo, times(2)).findAll();
    }

    @Test
    public void testDeletePlaceNoMatch() {
        Long placeId = 5L;

        when(this.placeRepo.findAll()).thenReturn(this.placeList);

        String result = this.placeService.deletePlace(placeId);

        assertThat(result).isEqualTo("No Id Matched");

        verify(this.placeRepo, times(1)).findAll();
    }
}

