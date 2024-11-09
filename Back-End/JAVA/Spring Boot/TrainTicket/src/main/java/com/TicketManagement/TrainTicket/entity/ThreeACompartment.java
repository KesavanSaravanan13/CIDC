package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "threea_compartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreeACompartment {

    @Id
    private Long threeACompartmentId;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
}
