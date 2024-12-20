package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "ticket_num")
    private Long ticketNumber;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "status")
    private boolean status;

    public boolean getStatus() {
        return this.status;
    }
}
