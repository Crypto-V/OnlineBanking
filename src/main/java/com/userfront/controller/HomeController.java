package com.userfront.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.userfront.domain.*;
import com.userfront.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userfront.domain.security.dao.RoleDao;
import com.userfront.domain.security.UserRole;
import com.userfront.service.UserService;

@Controller
public class HomeController {

    private final UserService userService;
    private final RoleDao roleDao;
    private final AppointmentService appointmentService;

    public HomeController(UserService userService, RoleDao roleDao, AppointmentService appointmentService) {
        this.userService = userService;
        this.roleDao = roleDao;
        this.appointmentService = appointmentService;
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUsernameExists(user.getUsername()) || userService.checkEmailExists(user.getEmail()) || userService.isPasswordValid(user.getPassword())) {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            if(!userService.isPasswordValid(user.getPassword())){
                model.addAttribute("passwordWrongFormat",true);
            }

            return "signup";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }

    @RequestMapping("/userFront")
    public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);


        return "userFront";
    }

    @RequestMapping("/adminFront")
    public String adminFront(Model model) {
//        User user = userService.findByUsername(principal.getName());
        List<User> users = userService.getAllUsers();
//        List<Appointment> appointments = appointmentService.findAll();
        model.addAttribute("users", users);
//        model.addAttribute("appointments", appointments);


        return "adminFront";
    }

    @RequestMapping("/adminAppointments")
    public String appointments(Model model) {
//        User user = userService.findByUsername("vasika");

//        List<User> users = userService.getAllUsers();
        List<Appointment> appointments = appointmentService.findAll();

//        model.addAttribute("users", users);
        model.addAttribute("appointments", appointments);

        return "adminAppointments";
    }

}
