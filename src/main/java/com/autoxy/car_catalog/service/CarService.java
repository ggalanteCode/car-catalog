package com.autoxy.car_catalog.service;

import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarEntity createNewCar(CarEntity entity) {
        return carRepository.save(entity);
    }

    public CarEntity readCarById(long id) {
        return carRepository.getReferenceById(id);
    }

    public List<CarEntity> readAllCars() {
        return carRepository.findAll();
    }

    //TODO update

    //TODO delete

}
