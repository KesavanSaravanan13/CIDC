package com.TicketManagement.TrainTicket.repository;

import com.TicketManagement.TrainTicket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
