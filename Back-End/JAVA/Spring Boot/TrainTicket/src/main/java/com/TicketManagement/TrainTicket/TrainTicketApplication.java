package com.TicketManagement.TrainTicket;

import com.TicketManagement.TrainTicket.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainTicketApplication.class, args);
    }

}
