package com.autoxy.car_catalog.service;

import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public CarEntity createNewCar(CarEntity entity) {
        return carRepository.save(entity);
    }

    //TODO read

    //TODO update

    //TODO delete

}
