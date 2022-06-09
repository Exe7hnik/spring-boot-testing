package ru.alishev.springcourse.FirstSecurityApp.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.models.Car;
import ru.alishev.springcourse.FirstSecurityApp.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car show(int id) {
        return carRepository.getById(id);
    }

    @Transactional
    public void deleteCar(int id) {
        carRepository.deleteCarByIdIs(id);
    }

    public Car createOrUpdateCar(Car car)
    {
        if(car.getId() == 0)
        {
            car = carRepository.save(car);

            return car;
        }
        else
        {
            Optional<Car> carUpdate = carRepository.findById(car.getId());
           // 1,купе,бмв,красный,оч хорошая машина,два литра,333,232,1000,true

            if(carUpdate.isPresent())
            {
                Car newCar = carUpdate.get();
                newCar.setBrand(car.getBrand());
                newCar.setModel(car.getModel());
                newCar.setColor(car.getColor());
                newCar.setBody_type(car.getBody_type());
                newCar.setEngine_type(car.getEngine_type());
                newCar.setHorse_power(car.getHorse_power());
                newCar.setPrice_per_day(car.getPrice_per_day());
                newCar.setView_or_not(car.isView_or_not());
                newCar.setDescription(car.getDescription());

                newCar = carRepository.save(newCar);

                return newCar;
            } else {
                car = carRepository.save(car);

                return car;
            }
        }
    }


}
