package com.userfront.controller;

import java.security.Principal;
import java.util.List;

import com.userfront.domain.Recipient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.userfront.domain.User;
import com.userfront.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user") User newUser, Model model) {
        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        model.addAttribute("user", user);

        userService.saveUser(user);

        return "profile";
    }

    @RequestMapping(value = "/{username}/enable", method = RequestMethod.GET)
    public String enableUser(@PathVariable("username") String username) {
        userService.enableUser(username);
        return "redirect:/adminFront";
    }

    @RequestMapping(value = "/{username}/disable", method = RequestMethod.GET)
    public String disableUser(@PathVariable("username") String username) {
        userService.disableUser(username);
        return "redirect:/adminFront";
    }


}

