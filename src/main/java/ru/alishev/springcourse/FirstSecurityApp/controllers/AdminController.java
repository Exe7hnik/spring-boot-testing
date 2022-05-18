package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.services.AdminService;
import ru.alishev.springcourse.FirstSecurityApp.services.BookingService;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    private final PersonDetailsService personDetailsService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    public AdminController(AdminService adminService, PersonDetailsService personDetailsService) {
        this.adminService = adminService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();

        return "admin";
    }

    @GetMapping("/admin/requests")
    public String adminRequests ( Model model) {
        model.addAttribute("bookings", bookingService.getAllBooking());
        return "requests";
    }

    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        model.addAttribute("users", personDetailsService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", personDetailsService.show(id));
        return "editUser";
    }

/*   @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") Person person, *//*BindingResult bindingResult,*//*
                         @PathVariable("id") int id) {
*//*
        if (bindingResult.hasErrors())
            return "editUser";
*//*

        personDetailsService.update(id, person);
        return "redirect:/admin/users";
    }*/



}
