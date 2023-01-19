package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.security.Principal;

@Controller
@RequestMapping()
public class AdminController {

    private final UsersService usersService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UsersService usersService, RoleService roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("admin", usersService.getUserByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("rolesAdd", roleService.getRoles());
        return "admin_page";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        usersService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        usersService.updateUser(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}/delete")
    public String removeUserById(@PathVariable("id") Long id) {
        usersService.removeUserById(id);
        return "redirect:/admin";
    }
}
