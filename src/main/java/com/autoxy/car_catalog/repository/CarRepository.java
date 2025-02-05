package com.autoxy.car_catalog.repository;

import com.autoxy.car_catalog.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    public List<CarEntity> findByBrand(String brand);

}
