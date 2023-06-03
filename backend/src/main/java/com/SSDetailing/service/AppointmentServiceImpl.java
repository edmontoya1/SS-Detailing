package com.SSDetailing.service;

import com.SSDetailing.entity.Appointment;
import com.SSDetailing.entity.Customer;
import com.SSDetailing.error.AppointmentNotFoundException;
import com.SSDetailing.error.CustomerNotFoundException;
import com.SSDetailing.repository.AppointmentRepository;
import com.SSDetailing.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Appointment> getAllValidAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.isValid())
                .collect(Collectors.toList());
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

    @Override
    public Appointment deleteAppointmentById(Long id) throws AppointmentNotFoundException {
        Optional<Appointment> toDelete = appointmentRepository.findById(id);

        if (!toDelete.isPresent()) {
            throw new AppointmentNotFoundException("Appointment Does Not Exist");
        }

        appointmentRepository.deleteById(id);

        return toDelete.get();
    }
}
