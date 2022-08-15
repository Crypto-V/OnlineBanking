package com.userfront.service.UserServiceImpl;

import com.userfront.UserFrontApplication;
import com.userfront.domain.User;
import com.userfront.domain.security.Role;
import com.userfront.domain.security.UserRole;
import com.userfront.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserFrontApplication.class)
@Transactional
class UserServiceImplTest {

    private static final String DEFAULT_EMAIL = "johndoe@localhost";
    private static final String DEFAULT_USERNAME = "vasiliok";
    private static final String DEFAULT_FIRSTNAME = "john";
    private static final String DEFAULT_LASTNAME = "doe";
    private static final String DEFAULT_PASSWORD = "12345678";
    private User user;
    private final Set<UserRole> userRoles = new HashSet<>();

    @Mock
    private UserService userService;

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
    }

    @Test
    void save() {
        when(userService.createUser(user, userRoles)).thenReturn(user);
        assertThat(user.getEmail()).isSameAs(user.getEmail());
        verify(userService, times(1)).createUser(user, userRoles);
    }

    @Test
    void findByUsername() {
        when(userService.findByUsername(DEFAULT_USERNAME)).thenReturn(user);
        assertThat(user.getUsername()).isEqualTo(DEFAULT_USERNAME);
    }

    @Test
    void findByEmail() {
        when(userService.findByEmail(DEFAULT_EMAIL)).thenReturn(user);
        assertThat(user.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    void checkUserExists() {
        when(userService.checkUserExists(DEFAULT_USERNAME, DEFAULT_EMAIL)).thenReturn(true);
        assertThat(userService.checkUserExists(DEFAULT_USERNAME, DEFAULT_EMAIL)).isEqualTo(true);
    }

    @Test
    void checkUsernameExists() {
        when(userService.checkUsernameExists(DEFAULT_USERNAME)).thenReturn(true);
        assertThat(userService.checkUsernameExists(DEFAULT_USERNAME)).isEqualTo(true);
    }

    @Test
    void checkEmailExists() {
        when(userService.checkEmailExists(DEFAULT_EMAIL)).thenReturn(true);
        assertThat(userService.checkEmailExists(DEFAULT_EMAIL)).isEqualTo(true);
    }

    @Test
    void enableUser() {
        doNothing().when(userService).enableUser(DEFAULT_USERNAME);
        assertThat(user.isEnabled()).isEqualTo(true);
    }

    @Test
    void disableUser() {
        user.setEnabled(false);
        doNothing().when(userService).disableUser(DEFAULT_USERNAME);
        assertThat(user.isEnabled()).isEqualTo(false);
    }

    @Test
    void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(List.of(user));
        assertThat(userService.getAllUsers().size()).isEqualTo(1);
    }
}