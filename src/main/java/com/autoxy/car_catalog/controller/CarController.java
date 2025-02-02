package com.autoxy.car_catalog.controller;

import com.autoxy.car_catalog.dto.CarRequestDto;
import com.autoxy.car_catalog.dto.CarResponseDto;
import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.mapper.CarMapper;
import com.autoxy.car_catalog.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto request) {
        CarEntity entity = carMapper.requestToEntity(request);
        entity = carService.createNewCar(entity);
        CarResponseDto response = carMapper.entityToResponse(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO get

    //TODO put

    //TODO delete

}
