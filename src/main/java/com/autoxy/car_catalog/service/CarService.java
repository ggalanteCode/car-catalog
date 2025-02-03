package com.autoxy.car_catalog.service;

import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.mapper.CarMapper;
import com.autoxy.car_catalog.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public CarEntity createNewCar(CarEntity entity) {
        return carRepository.save(entity);
    }

    public CarEntity readCarById(long id) {
        return carRepository.getReferenceById(id);
    }

    public List<CarEntity> readAllCars() {
        return carRepository.findAll();
    }

    public CarEntity updateCar(long id, CarEntity entity) {

        Optional<CarEntity> oldEntity = carRepository.findById(id);
        if (oldEntity.isEmpty()) {
            return carRepository.save(entity);
        }

        CarEntity entityToBeUpdated = oldEntity.get();
        entity.setId(id);

        carMapper.updateEntity(entity, entityToBeUpdated);

        carRepository.save(entityToBeUpdated);

        return entityToBeUpdated;
    }

    public void deleteCarById(long id) {
        carRepository.deleteById(id);
    }

}
