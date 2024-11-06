package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "3a_compartment")
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
    private boolean oneA;

    @Column(nullable = false)
    private boolean twoA;

    @Column(nullable = false)
    private boolean threeA;

    @Column(nullable = false)
    private boolean fourA;

    @Column(nullable = false)
    private boolean fiveA;

    @Column(nullable = false)
    private boolean oneB;

    @Column(nullable = false)
    private boolean twoB;

    @Column(nullable = false)
    private boolean threeB;

    @ManyToOne
    @JoinColumn(name = "sl_compartment_id", nullable = false)
    private SlCompartment slCompartment;

    @ManyToOne
    @JoinColumn(name = "2a_compartment_id", nullable = false)
    private TwoACompartment twoACompartment;

    @ManyToOne
    @JoinColumn(name = "3a_compartment_id", nullable = false)
    private ThreeACompartment threeACompartment;


}
