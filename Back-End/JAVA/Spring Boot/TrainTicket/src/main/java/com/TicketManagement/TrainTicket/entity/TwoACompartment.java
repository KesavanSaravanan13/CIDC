package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "2a_compartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwoACompartment {

    @Id
    private Long twoACompartmentId;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
}
