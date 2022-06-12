package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alishev.springcourse.FirstSecurityApp.models.Booking;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;
import ru.alishev.springcourse.FirstSecurityApp.services.AdminService;
import ru.alishev.springcourse.FirstSecurityApp.services.BookingService;
import ru.alishev.springcourse.FirstSecurityApp.services.CarService;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    private final AdminService adminService;

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    public MainController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("listCars", carService.getAllCars());

        return "index";
    }

    @GetMapping("/contacts")
    public String contactsPage() {

        return "contacts";
    }

    @GetMapping("/cars")
    public String carsPage(Model model) {
        model.addAttribute("listCars", carService.getAllCars());

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

            model.addAttribute("personId", personDetails.getPersonId());
        }

        return "cars";
    }

    @GetMapping("cars/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("bookings", bookingService.getAllBooking());
        model.addAttribute("car", carService.show(id));
        return "car_show";
    }

/*    @GetMapping("/profile/{id}")
    public String personShow(@PathVariable("id") int id, Model model) {
        
        model.addAttribute("personBooking", bookingService.showBooking(id));

       // model.addAttribute("bookings", bookingService.getAllBooking());

        return "profile";
    }*/

        @GetMapping("/profile")
    public String personShow( Model model) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

            System.out.println("ДЕБАГГГ  " + bookingService.showBooking(personDetails.getPersonId()));

            if(bookingService.showBooking(personDetails.getPersonId()).equals(null)) {
                return "ass";
            }

           // List<Booking> books = bookingService.getAllBooking();
           // System.out.println(books);

            // ПЕРЕДЕЛАТЬ
            if (bookingService.showBooking(personDetails.getPersonId()) != null) {
                try {
                    model.addAttribute("personBooking", bookingService.showBooking(personDetails.getPersonId()));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            model.addAttribute("person",personDetails.getPerson());

       // model.addAttribute("bookings", bookingService.getAllBooking());

        return "profile";
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
        System.out.println("id пользователя" + personDetails.getPerson().getId());

        return "hello";
    }

}
