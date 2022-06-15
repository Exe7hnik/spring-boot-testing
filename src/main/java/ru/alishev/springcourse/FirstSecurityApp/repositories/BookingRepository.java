package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Booking;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("from Booking b inner join fetch b.person where b.person.id = :id")
    List<Booking> findByBookingPersonId(@Param("id") int id);

    List<Booking> findBookingByPersonId(@Param("id") int id);


    List<Booking> findBookingByStatusIsTrue();

    List<Booking> findBookingByStatusIsFalse();



}
