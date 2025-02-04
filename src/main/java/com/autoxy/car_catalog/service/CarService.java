package com.autoxy.car_catalog.service;

import com.autoxy.car_catalog.entity.CarEntity;

import java.util.List;

public interface CarService {

    CarEntity createNewCar(CarEntity entity);
    CarEntity readCarById(long id);
    List<CarEntity> readAllCars();
    CarEntity updateCar(long id, CarEntity entity);
    void deleteCarById(long id);

}
