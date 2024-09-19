package com.TicketManagement.TrainTicket.Repository;

import com.TicketManagement.TrainTicket.Table.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {
}
