package com.userfront.service.UserServiceImpl;

import java.util.List;
import java.util.Set;

import com.userfront.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.domain.security.dao.RoleDao;
import com.userfront.domain.security.dao.UserDao;
import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;
import com.userfront.service.AccountService;
import com.userfront.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountService accountService;
    private final EmailService emailService;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder, AccountService accountService, EmailService emailService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.emailService = emailService;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());
        String pass = user.getPassword();

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());
            // Generating a custom username
            int generatedNumber = (int) (Math.random() * 100 + 0);
            user.setUsername(user.getUsername() + generatedNumber);
            localUser = userDao.save(user);
            emailService.sendEmail(user.getEmail(), localUser.getPrimaryAccount().getAccountNumber(), localUser.getUsername(), pass);
        }

        return localUser;
    }
    
    public boolean checkUserExists(String username, String email){
        return checkUsernameExists(username) || checkEmailExists(username);
    }

    public boolean checkUsernameExists(String username) {
        return null != findByUsername(username);
    }

    public boolean checkEmailExists(String email) {
        return null != findByEmail(email);
    }

    public User saveUser (User user) {
        return userDao.save(user);
    }
    
    public List<User> findUserList() {
        return userDao.findAll();
    }

    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
