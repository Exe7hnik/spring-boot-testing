package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    void deleteCarByIdIs(int id);
}
