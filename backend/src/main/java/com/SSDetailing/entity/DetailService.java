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
    @SequenceGenerator(
            name = "detail_service_sequence",
            sequenceName = "detail_service_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "detail_service_sequence"
    )
    private Long detailServiceId;

    private String type;

    private double price;

    private Long appointment_id;
}
