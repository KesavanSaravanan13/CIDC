package com.TicketManagement.TrainTicket.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sl_compartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlCompartment {

    @Id
    private Long slCompartmentId;

    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;
}
