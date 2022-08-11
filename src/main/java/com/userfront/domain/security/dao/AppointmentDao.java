package com.userfront.domain.security.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.Appointment;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDao extends CrudRepository<Appointment, Long> {
    List<Appointment> findAll();
}
