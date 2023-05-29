package com.SSDetailing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "detail_service_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id")
    private Appointment appointment;
}
