package com.TicketManagement.TrainTicket.Table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.java.LocaleJavaType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket_details")
@Getter
@Setter
public class TicketDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketNum;

    @Column(name = "date_of_booking")
    private LocalDate dateOfBooking;

    @Column(name = "date_of_travel")
    private LocalDate dateOfTravel;

    @Column(name = "travel_timing")
    private LocalTime travelTiming;

    @Column(name = "travel_from")
    private String travelFrom;

    @Column(name = "travel_to")
    private String travelTo;

    @Column(name = "no_of_days_travel")
    private Integer noOfDaysTravel;

    @Column(name = "prebook_food")
    private Boolean prebookFood;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDetails user;


}
