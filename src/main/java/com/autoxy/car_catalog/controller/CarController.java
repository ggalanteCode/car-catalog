package com.autoxy.car_catalog.controller;

import com.autoxy.car_catalog.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    //TODO post

    //TODO get

    //TODO put

    //TODO delete

}
