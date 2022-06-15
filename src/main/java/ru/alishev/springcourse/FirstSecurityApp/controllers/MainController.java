package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.alishev.springcourse.FirstSecurityApp.models.Booking;
import ru.alishev.springcourse.FirstSecurityApp.models.Car;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;
import ru.alishev.springcourse.FirstSecurityApp.services.AdminService;
import ru.alishev.springcourse.FirstSecurityApp.services.BookingService;
import ru.alishev.springcourse.FirstSecurityApp.services.CarService;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
public class MainController {
    private final AdminService adminService;

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private final PersonDetailsService personDetailsService;

    @Autowired
    public MainController(AdminService adminService, PersonDetailsService personDetailsService) {
        this.adminService = adminService;
        this.personDetailsService = personDetailsService;
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

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

            model.addAttribute("personId", personDetails.getPersonId());
        }

        model.addAttribute("car", carService.show(id));
        model.addAttribute("booking", new Booking());
        tempCarId = id;

        return "car_show";
    }


int tempCarId;

    @RequestMapping(path = "/cars/{id}", method = RequestMethod.POST)
    public String addReservation(Booking booking) {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

          Person person = personDetailsService.show(personDetails.getPersonId());
            booking.setPerson(person);

          Car car = carService.show(tempCarId);
            booking.setCar(car);

            int tempPrice = car.getPrice_per_day();

            Date dateBefore = booking.getDate_start();
            Date dateAfter = booking.getDate_end();

            long dateBeforeInMs = dateBefore.getTime();
            long dateAfterInMs = dateAfter.getTime();

            long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);

            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

            System.out.println(" The number of days between dates: " + daysDiff);

            int carPriceCalc = (int) (daysDiff * tempPrice);

            booking.setTotal_price(carPriceCalc);

        System.out.println(booking);
        }

        bookingService.createBooking(booking);
        return "index";
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

            //System.out.println("ДЕБАГГГ  " + bookingService.showBooking(personDetails.getPersonId()));

           // List<Booking> books = bookingService.getAllBooking();
           // System.out.println(books);

            //System.out.println("ПРОВЕРКА" + bookingService.findByBookingPersonId(personDetails.getPersonId()));

            if (bookingService.findByBookingPersonId(personDetails.getPersonId()) != null) {
                model.addAttribute("personBooking", bookingService.findBookingByPersonId(personDetails.getPersonId()));
                System.out.println(bookingService.findBookingByPersonId(personDetails.getPersonId()));
            }
            //model.addAttribute("personBooking", bookingService.findByBookingPersonId(personDetails.getPersonId()));

            // ПЕРЕДЕЛАТЬ
/*            if (bookingService.showBooking(personDetails.getPersonId()) != null) {
                try {
                    model.addAttribute("personBooking", bookingService.showBooking(personDetails.getPersonId()));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }*/

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
