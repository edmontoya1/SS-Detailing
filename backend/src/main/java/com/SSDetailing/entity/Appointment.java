package com.SSDetailing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_sequence"
    )
    private Long appointmentId;

    private LocalDateTime date;

    private Long customer_Id;

    private boolean isValid;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointmentId")
    private List<DetailService> detailServiceList;
}
