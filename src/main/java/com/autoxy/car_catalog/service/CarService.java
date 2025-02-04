package com.autoxy.car_catalog.service;

import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.exceptions.CarNotFoundException;
import com.autoxy.car_catalog.exceptions.CarStatusValueException;
import com.autoxy.car_catalog.exceptions.NoCarExistsException;
import com.autoxy.car_catalog.mapper.CarMapper;
import com.autoxy.car_catalog.repository.CarRepository;
import com.autoxy.car_catalog.validator.CarValidator;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public CarEntity createNewCar(CarEntity entity)
            throws PropertyValueException, DateTimeParseException, CarStatusValueException, NullPointerException {
        if (!CarValidator.isCarStatusValid(entity)) {
            throw new CarStatusValueException("Car status can be only \"available\" or \"sold\".");
        }
        CarValidator.validateCarYear(entity);
        return carRepository.save(entity);
    }

    public CarEntity readCarById(long id) throws CarNotFoundException {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car with id: " + id + " doesn't exist!"));
    }

    public List<CarEntity> readAllCars() throws NoCarExistsException {
        List<CarEntity> entities = carRepository.findAll();
        if (entities.isEmpty()) {
            throw new NoCarExistsException("No Car exists at the moment!");
        }
        return entities;
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
