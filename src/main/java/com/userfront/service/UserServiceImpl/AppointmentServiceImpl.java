package com.userfront.service.UserServiceImpl;

import java.util.List;

import com.userfront.service.EmailService;
import org.springframework.stereotype.Service;

import com.userfront.domain.security.dao.AppointmentDao;
import com.userfront.domain.Appointment;
import com.userfront.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDao appointmentDao;
    private final EmailService emailService;

    public AppointmentServiceImpl(AppointmentDao appointmentDao, EmailService emailService) {
        this.appointmentDao = appointmentDao;
        this.emailService = emailService;
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    public Appointment findAppointment(Long id) {
        return appointmentDao.findById(id).orElseGet(null);
    }

    public void confirmAppointment(Long id) {
        Appointment appointment = findAppointment(id);
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
        emailService.sendAppointmentEmail(appointment.getUser().getEmail(), appointment.getUser().getPrimaryAccount().getAccountNumber(), appointment.getDescription(), appointment.getDate());
    }
}
