package com.autoxy.car_catalog.controller;

import com.autoxy.car_catalog.dto.*;
import com.autoxy.car_catalog.entity.CarEntity;
import com.autoxy.car_catalog.mapper.CarMapper;
import com.autoxy.car_catalog.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto request) throws HttpMessageNotReadableException {
        CarEntity entity = carMapper.requestToEntity(request);
        entity = carService.createNewCar(entity);
        CarResponseDto response = carMapper.entityToResponse(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<CarResponseDto> readCarById(@PathVariable String id) {
        CarEntity entity = carService.readCarById(Long.parseLong(id));
        CarResponseDto response = carMapper.entityToResponse(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<CarResponseDto>> readAllCars() {
        List<CarEntity> entities = carService.readAllCars();
        List<CarResponseDto> responses = carMapper.entitiesToResponses(entities);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable String id,
                                                    @RequestBody CarRequestDto request) throws HttpMessageNotReadableException {
        CarEntity entity = carMapper.requestToEntity(request);
        entity = carService.updateCar(Long.parseLong(id), entity);
        CarResponseDto response = carMapper.entityToResponse(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ResponseEntity<String> deleteCarById(@PathVariable String id) {
        carService.deleteCarById(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/brand/{brand}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<CarResponseDto>> readCarsByBrand(@PathVariable String brand) {
        List<CarEntity> entities = carService.readCarsByBrand(brand);
        List<CarResponseDto> responses = carMapper.entitiesToResponses(entities);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/byPriceRange")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<CarResponseDto>> readCarsByPriceRange(@RequestParam String fromPrice,
                                                                @RequestParam String toPrice) {
        List<CarEntity> entities = carService.readCarsByPriceRange(Double.parseDouble(fromPrice),
                Double.parseDouble(toPrice));
        List<CarResponseDto> responses = carMapper.entitiesToResponses(entities);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<List<CarResponseDto>> readCarsByStatus(@PathVariable String status) {
        List<CarEntity> entities = carService.readCarsByStatus(status);
        List<CarResponseDto> responses = carMapper.entitiesToResponses(entities);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}
