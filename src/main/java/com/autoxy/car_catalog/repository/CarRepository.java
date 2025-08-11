package com.autoxy.car_catalog.repository;

import com.autoxy.car_catalog.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findByBrand(String brand);

    List<CarEntity> findByPriceBetween(double minPrice, double maxPrice);

    List<CarEntity> findByStatus(String status);

}
