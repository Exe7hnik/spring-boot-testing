package ru.alishev.springcourse.FirstSecurityApp.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Booking;
import ru.alishev.springcourse.FirstSecurityApp.models.Car;
import ru.alishev.springcourse.FirstSecurityApp.repositories.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking showBooking(int id) {
        return bookingRepository.getById(id);
    }

    public void updateBooking(int id) {
        bookingRepository.getById(id);

    }



}
