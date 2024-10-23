package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TicketDetailsControllerTest {

    @InjectMocks
    private TicketDetailsController ticketDetailsController;

    @Mock
    private TicketService ticketDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTickets_ShouldReturnListOfTickets() {
        TicketDTO ticket1 = new TicketDTO();
        ticket1.setTicketId(1L);
        ticket1.setTicketNumber(3L);

        TicketDTO ticket2 = new TicketDTO();
        ticket2.setTicketId(2L);
        ticket2.setTicketNumber(3L);

        when(ticketDetailsService.getAllTickets()).thenReturn(Arrays.asList(ticket1, ticket2));

        List<TicketDTO> result = ticketDetailsController.getAllTickets();

        assertEquals(2, result.size());
        assertEquals(3L, result.get(0).getTicketNumber());
    }

    @Test
    void getTicketById_ShouldReturnTicketDTO() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
        ticketDTO.setTicketNumber(3L);

        when(ticketDetailsService.getTicketById(1L)).thenReturn(ticketDTO);

        TicketDTO result = ticketDetailsController.getTicketById(1L);

        assertEquals(3L, result.getTicketNumber());
    }

    @Test
    void createTicket_ShouldCallSaveTicket() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(1L);
        ticketDTO.setTicketNumber(3L);

        ticketDetailsController.createTicket(ticketDTO);

        verify(ticketDetailsService, times(1)).saveTicket(ticketDTO);
    }

    @Test
    void deleteTicket_ShouldReturnDeletedMessage() {
        when(ticketDetailsService.deleteTicket(1L)).thenReturn("Deleted");

        String result = ticketDetailsController.deleteTicket(1L);

        assertEquals("Deleted", result);
        verify(ticketDetailsService, times(1)).deleteTicket(1L);
    }

    @Test
    void deleteTicket_ShouldReturnNoIdMatchedMessage() {
        when(ticketDetailsService.deleteTicket(1L)).thenReturn("No Id Matched");

        String result = ticketDetailsController.deleteTicket(1L);

        assertEquals("No Id Matched", result);
        verify(ticketDetailsService, times(1)).deleteTicket(1L);
    }
}
