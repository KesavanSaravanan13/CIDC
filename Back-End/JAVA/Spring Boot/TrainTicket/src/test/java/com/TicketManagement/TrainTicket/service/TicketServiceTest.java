package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.exception.NoDataFoundException;
import com.TicketManagement.TrainTicket.repository.TicketDetailsRepository;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TicketDetailsRepository ticketRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTicketDetails_ShouldReturnListOfActiveTickets() {
        TicketDetails ticket1 = new TicketDetails();
        ticket1.setTicketId(1L);
        ticket1.setTicketNumber(3L);
        ticket1.setStatus(true);

        TicketDetails ticket2 = new TicketDetails();
        ticket2.setTicketId(2L);
        ticket2.setTicketNumber(3L);
        ticket2.setStatus(false);

        when(ticketRepo.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        List<TicketDTO> result = ticketService.getTicketDetails();

        assertEquals(1, result.size());
        assertEquals(3L, result.get(0).getTicketNumber());
    }

    @Test
    void getTicketDetails_ShouldThrowNoDataFoundExceptionWhenNoActiveTickets() {

        when(ticketRepo.findAll()).thenReturn(Arrays.asList());

        Exception exception = assertThrows(NoDataFoundException.class, () -> {
            ticketService.getTicketDetails();
        });
        assertEquals("No Data Found", exception.getMessage());
    }

    @Test
    void saveTicket_ShouldSaveTicketSuccessfully() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
        ticketDTO.setTicketNumber(3L);
        ticketDTO.setStatus(true);
        User user = new User();
        user.setUserId(1L);
        ticketDTO.setUser(user);

        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketId(1L);
        ticketDetails.setTicketNumber(3L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ticketRepo.save(any(TicketDetails.class))).thenReturn(ticketDetails);

        ticketService.saveTicket(ticketDTO);

        verify(ticketRepo, times(1)).save(any(TicketDetails.class));
    }

    @Test
    void saveTicket_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
        ticketDTO.setTicketNumber(3L);
        ticketDTO.setStatus(true);
        User user = new User();
        user.setUserId(1L);
        ticketDTO.setUser(user);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ticketService.saveTicket(ticketDTO);
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void getTicketById_ShouldReturnTicketDTO() {
        // Arrange
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketId(1L);
        ticketDetails.setTicketNumber(3L);

        when(ticketRepo.findById(1L)).thenReturn(Optional.of(ticketDetails));

        TicketDTO result = ticketService.getTicketById(1L);

        assertEquals(3L, result.getTicketNumber());
    }

    @Test
    void getTicketById_ShouldThrowNotFoundExceptionWhenTicketDoesNotExist() {
        when(ticketRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ticketService.getTicketById(1L);
        });
        assertEquals("Not Found", exception.getMessage());
    }

    @Test
    void deleteTicket_ShouldReturnDeletedMessage() {
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketId(1L);
        ticketDetails.setStatus(true);

        when(ticketRepo.findById(1L)).thenReturn(Optional.of(ticketDetails));

        String result = ticketService.deleteTicket(1L);

        assertEquals("Deleted", result);
        assertFalse(ticketDetails.getStatus());
        verify(ticketRepo, times(1)).save(ticketDetails);
    }

    @Test
    void deleteTicket_ShouldReturnNoIdMatchedMessage() {
        when(ticketRepo.findById(1L)).thenReturn(Optional.empty());

        String result = ticketService.deleteTicket(1L);

        assertEquals("No Id Matched", result);
        verify(ticketRepo, never()).save(any());
    }


    @Test
    void getAllTickets_ShouldReturnListOfTickets() {
        TicketDetails ticketDetails1 = new TicketDetails();
        ticketDetails1.setTicketId(1L);
        ticketDetails1.setTicketNumber(3L);
        ticketDetails1.setStatus(true);

        TicketDetails ticketDetails2 = new TicketDetails();
        ticketDetails2.setTicketId(2L);
        ticketDetails2.setTicketNumber(4L);
        ticketDetails2.setStatus(true);

        when(ticketRepo.findAll()).thenReturn(Arrays.asList(ticketDetails1, ticketDetails2));

        List<TicketDTO> result = ticketService.getAllTickets();

        assertEquals(2, result.size());
        assertEquals(3L, result.get(0).getTicketNumber());
        assertEquals(4L, result.get(1).getTicketNumber());
    }

    @Test
    void getAllTickets_ShouldThrowNoDataFoundException_WhenNoTickets() {
        when(ticketRepo.findAll()).thenReturn(new ArrayList<>());

        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () -> {
            ticketService.getAllTickets();
        });
        assertEquals("No Data Found", exception.getMessage());
    }
}
