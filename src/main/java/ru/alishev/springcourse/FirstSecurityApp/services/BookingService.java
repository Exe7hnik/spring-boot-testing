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

    public List<Booking> findByBookingPersonId(int id) {
        return bookingRepository.findByBookingPersonId(id);
    }

    public List<Booking> findBookingByPersonId(int id) {
        return bookingRepository.findBookingByPersonId(id);
    }

    public List<Booking> findByTrueStatus() {
        return bookingRepository.findBookingByStatusIsTrue();
    }

    public List<Booking> findByFalseStatus() {
        return bookingRepository.findBookingByStatusIsFalse();
    }

    public void createBooking(Booking booking) {
        bookingRepository.save(booking);
    }

}
