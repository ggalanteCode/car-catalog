package com.autoxy.car_catalog;

import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.repository.CarRepository;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarRepositoryUnitTests {

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("Test 1: Create New Car Test")
    @Order(1)
    @Rollback(value = false)
    void createNewCarTest() {
        //Action
        CarEntity car = new CarEntity();
        car.setBrand("Peugeot");
        car.setModel("208");
        car.setYearOfProduction("2025");
        car.setPrice(5000.00);
        car.setStatus("available");

        carRepository.save(car);

        //Verify
        System.out.println(car);
        assertThat(car.getId(), is(greaterThan(0L)));
    }

    @Test
    @DisplayName("Test 2: Read Car By Id Test")
    @Order(2)
    //@Disabled
    void readCarByIdTest() {
        //Action
        long id = 1L;
        CarEntity car = carRepository.findById(id).get();

        //Verify
        System.out.println(car);
        assertThat(car.getId(), is(equalTo(1L)));
    }

    @Test
    @DisplayName("Test 3: Read All Cars Test")
    @Order(3)
    //@Disabled
    void readAllCarsTest() {
        //Action
        List<CarEntity> cars = carRepository.findAll();

        //Verify
        System.out.println(cars);
        assertThat(cars.size(), is(greaterThan(0)));
    }

    @Test
    @DisplayName("Test 4: Update Car Test")
    @Order(4)
    @Rollback(value = false)
    //@Disabled
    void updateCarTest() {
        //Action
        long id = 1L;
        CarEntity oldCar = carRepository.findById(id).get();
        System.out.println("OLD CAR: " + oldCar);
        oldCar.setPrice(6000.00);
        oldCar.setStatus("sold");
        CarEntity updatedCar = carRepository.save(oldCar);

        //Verify
        System.out.println("UPDATED CAR: " + updatedCar);
        assertThat(updatedCar.getPrice(), is(equalTo(6000.00)));
        assertThat(updatedCar.getStatus(), is(equalTo("sold")));
    }

    @Test
    @DisplayName("Test 5: Delete Car By Id Test")
    @Order(5)
    @Rollback(value = false)
    //@Disabled
    void deleteCarByIdTest() {
        //Action
        long id = 1L;
        boolean thisCarExists = carRepository.existsById(id);
        carRepository.deleteById(id);
        List<CarEntity> cars = carRepository.findAll();

        //Verify
        System.out.println(cars);
        assertThat(thisCarExists, is(true));
        assertThat(cars.size(), is(equalTo(0)));
    }

}
