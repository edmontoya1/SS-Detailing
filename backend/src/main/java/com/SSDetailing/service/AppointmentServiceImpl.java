package com.SSDetailing.service;

import com.SSDetailing.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;
}
