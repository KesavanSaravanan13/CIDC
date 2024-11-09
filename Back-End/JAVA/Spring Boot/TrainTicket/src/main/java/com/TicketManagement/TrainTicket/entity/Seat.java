package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(nullable = false)
    private boolean seatOneA;

    @Column(nullable = false)
    private boolean seatTwoA;

    @Column(nullable = false)
    private boolean seatThreeA;

    @Column(nullable = false)
    private boolean seatFourA;

    @Column(nullable = false)
    private boolean seatFiveA;

    @Column(nullable = false)
    private boolean seatSixA;

    @Column(nullable = false)
    private boolean seatOneB;

    @Column(nullable = false)
    private boolean seatTwoB;

    @Column(nullable = false)
    private boolean seatThreeB;

    @Column(nullable = false)
    private boolean seatFourB;

    @ManyToOne
    @JoinColumn(name = "sl_compartment_id")
    private SlCompartment slCompartment;

    @ManyToOne
    @JoinColumn(name = "twoa_compartment_id")
    private TwoACompartment twoACompartment;

    @ManyToOne
    @JoinColumn(name = "threea_compartment_id")
    private ThreeACompartment threeACompartment;


}
