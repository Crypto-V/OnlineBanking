package com.userfront.service;

import java.util.Date;

public interface EmailService {
    void sendEmail(String email, int accountNumber, String username, String password);
    void sendAppointmentEmail(String email, int accountNumber, String description, Date date);
}
