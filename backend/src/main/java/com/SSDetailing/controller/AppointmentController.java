package com.SSDetailing.controller;

import com.SSDetailing.entity.Appointment;
import com.SSDetailing.error.AppointmentNotFoundException;
import com.SSDetailing.error.CustomerNotFoundException;
import com.SSDetailing.service.AppointmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/valid")
    public List<Appointment> getAllValidAppointments() { return appointmentService.getAllValidAppointments(); }

    @PostMapping("/{customerId}")
    public Appointment createAppointment(@Valid @RequestBody Appointment appointment,
                                         @PathVariable("customerId") Long id) throws CustomerNotFoundException {
        return appointmentService.createAppointment(appointment, id);
    }

    @DeleteMapping("/{appointmentId}")
    public Appointment deleteAppointmentById(@PathVariable("appointmentId") Long id) throws AppointmentNotFoundException {
        return appointmentService.deleteAppointmentById(id);
    }
}
