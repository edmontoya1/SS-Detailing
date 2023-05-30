package com.SSDetailing.service;

import com.SSDetailing.entity.Appointment;
import com.SSDetailing.entity.Customer;
import com.SSDetailing.error.CustomerNotFoundException;
import com.SSDetailing.repository.AppointmentRepository;
import com.SSDetailing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Long id) throws CustomerNotFoundException {
        Optional<Customer> toUpdate = customerRepository.findById(id);

        if (!toUpdate.isPresent()) {
            throw new CustomerNotFoundException("Customer Not Found");
        }

        Customer customer = toUpdate.get();

        if (!customer.getAppointmentList().contains(appointment)) {
            appointment.setCustomer_Id(customer.getCustomerId());
            customerRepository.save(customer);
        }

        return appointmentRepository.save(appointment);
    }
}
