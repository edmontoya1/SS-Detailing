package com.SSDetailing.service;

import com.SSDetailing.entity.Appointment;
import com.SSDetailing.error.AppointmentNotFoundException;
import com.SSDetailing.error.CustomerNotFoundException;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();

    Appointment createAppointment(Appointment appointment, Long id) throws CustomerNotFoundException;

    Appointment deleteAppointmentById(Long id) throws AppointmentNotFoundException;
}
