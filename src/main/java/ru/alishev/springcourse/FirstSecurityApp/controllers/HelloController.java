package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;
import ru.alishev.springcourse.FirstSecurityApp.services.AdminService;
import ru.alishev.springcourse.FirstSecurityApp.services.BookingService;
import ru.alishev.springcourse.FirstSecurityApp.services.CarService;

/**
 * @author Neil Alishev
 */
@Controller
public class HelloController {
    private final AdminService adminService;

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("listCars", carService.getAllCars());
        return "index";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }
}
