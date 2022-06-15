package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.Car;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.services.AdminService;
import ru.alishev.springcourse.FirstSecurityApp.services.BookingService;
import ru.alishev.springcourse.FirstSecurityApp.services.CarService;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    private final PersonDetailsService personDetailsService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CarService carService;

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
        model.addAttribute("bookings", bookingService.findByFalseStatus());
        model.addAttribute("trueBookings", bookingService.findByTrueStatus());

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

    //@PatchMapping("/admin/{id}")  ОБВОНИТЬ ПОльзоваТЕЛЯ
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            System.out.println(bindingResult);

        personDetailsService.updatePerson(id, person);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/cars")
    public String allCarsAdmin(Model model) {
        model.addAttribute("listCars", carService.getAllCars());
        return "adminCars";
    }

/*    @GetMapping("/admin/cars/{id}")
    public String editCar(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carService.show(id));
        return "carEdit";
    }*/

   @RequestMapping(path = { "/admin/cars/{id}"})
    public String editCarById(Model model, @PathVariable("id") Optional<Integer> id)
    {
        if (id.isPresent()) {
            Car car = carService.show(id.get());
            model.addAttribute("car", car);
        } else {
            model.addAttribute("car", new Car());
        }

       // model.addAttribute("car", new Car());

        return "carEdit";
    }

    @RequestMapping(path = { "/admin/cars/new"})
    public String addNewCar(Model model) {
            model.addAttribute("car", new Car());
        return "newCar";
    }


/*    @RequestMapping(value = "/admin/cars/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return "redirect:/admin/cars";
    }*/


    @RequestMapping(path = "/admin/cars/{id}", method = RequestMethod.POST)
    public String createOrUpdateEmployee(Car car)
    {
        carService.createOrUpdateCar(car);
        return "redirect:/admin";
    }
}
