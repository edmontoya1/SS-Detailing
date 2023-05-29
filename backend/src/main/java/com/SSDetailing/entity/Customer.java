package com.SSDetailing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "Missing First Name")
    private String firstName;

    @NotBlank(message = "Missing Last Name")
    private String lastName;

    @Email
    private String email;

    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Column(name = "appointments")
    private List<Appointment> appointmentList;
}
