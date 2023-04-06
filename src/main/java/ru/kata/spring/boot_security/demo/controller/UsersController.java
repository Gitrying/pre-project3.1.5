package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.model.Role;

import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public String goToUserPage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserById(userService.findByUserEmail(authentication.getName()).getId()));
        return "user";
    }

    @GetMapping("/admin")
    public String goToAdminPage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserById(userService.findByUserEmail(authentication.getName()).getId()));
        List<Role> listRoles = userService.listRolesForAUser();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("listRoles", listRoles);
        return "admin";
    }
}