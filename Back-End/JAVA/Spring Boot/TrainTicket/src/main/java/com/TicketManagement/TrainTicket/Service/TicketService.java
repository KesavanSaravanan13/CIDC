package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.mapper.TicketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketMapper ticketMapper;
    private final List<TicketDTO> ticketList = new ArrayList<>();

    public void saveTicket(TicketDTO ticket) {
        ticketMapper.toDTO();
        new TicketDTO(ticket.getTicketId(), ticket.getTicketNumber(),
                ticket.getDateOfBooking(), ticket.getDateOfTravel(),
                ticket.getTravelTiming(), ticket.getTravelFrom(),
                ticket.getTravelTo(), ticket.getNoOfDaysTravel(),
                ticket.getPrebookFood(), ticket.getUser(), ticket.getStatus());
    }

    public TicketDTO getTicketById(Long id) {
        ticketMapper.toDTO();
        for (TicketDTO TicketDTO : ticketMapper.getTicketList()) {
            if (TicketDTO.getTicketId().equals(id)) {
                return TicketDTO;
            }
        }
        return null;
    }

    public List<TicketDTO> getAllTickets() {
        ticketMapper.toDTO();
        return new ArrayList<>(ticketMapper.getTicketList());
    }

    public String deleteTicket(Long ticketId) {
        final String[] str = {""};
        ticketMapper.toDTO();
        ticketMapper.getTicketList().forEach(user -> {
            if (user.getTicketId().equals(ticketId)) {
                user.setStatus("De-Active");
                ticketMapper.toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}

