package com.autoxy.car_catalog.controller;

import com.autoxy.car_catalog.dto.CarRequestDto;
import com.autoxy.car_catalog.dto.CarResponseDto;
import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ModelMapper carMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto request) {
        CarEntity entity = carMapper.typeMap(CarRequestDto.class, CarEntity.class)
                .addMappings(mapper -> mapper.skip(CarEntity::setId))
                .map(request);
        entity = carService.createNewCar(entity);
        CarResponseDto response = carMapper.map(entity, CarResponseDto.class);
        return ResponseEntity.ok(response);
    }

    //TODO get

    //TODO put

    //TODO delete

}
