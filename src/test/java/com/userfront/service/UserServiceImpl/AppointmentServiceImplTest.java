package com.userfront.service.UserServiceImpl;

import com.userfront.UserFrontApplication;
import com.userfront.domain.Appointment;
import com.userfront.domain.User;
import com.userfront.domain.security.Role;
import com.userfront.domain.security.UserRole;
import com.userfront.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = UserFrontApplication.class)
@Transactional
class AppointmentServiceImplTest {

    private static final String DEFAULT_EMAIL = "johndoe@localhost";
    private static final String DEFAULT_USERNAME = "vasiliok";
    private static final String DEFAULT_FIRSTNAME = "john";
    private static final String DEFAULT_LASTNAME = "doe";
    private static final String DEFAULT_PASSWORD = "12345678";
    private User user;
    private Appointment appointment;
    private final Set<UserRole> userRoles = new HashSet<>();
    @Mock
    private AppointmentService appointmentService;

    @BeforeEach
    public void init() {
        user = new User();
        user.setUsername(DEFAULT_USERNAME);
        user.setEnabled(true);
        user.setEmail(DEFAULT_EMAIL);
        user.setFirstName(DEFAULT_FIRSTNAME);
        user.setLastName(DEFAULT_LASTNAME);
        user.setPassword(DEFAULT_PASSWORD);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        Role role = new Role();
        role.setRoleId(-1);
        role.setName("TEST_ROLE");
        userRole.setRole(role);
        userRoles.add(userRole);
        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setUser(user);
        appointment.setConfirmed(true);
        appointment.setDate(new Date());
        appointment.setDescription("Test description");
        appointment.setLocation("London");
    }


    @Test
    void findAppointment() {
        when(appointmentService.findAppointment(1L)).thenReturn(appointment);
        assertThat(appointment.getLocation()).isEqualTo("London");
    }

    @Test
    void confirmAppointment() {
        doNothing().when(appointmentService).confirmAppointment(1L);
        assertThat(appointment.isConfirmed()).isEqualTo(true);
    }
}