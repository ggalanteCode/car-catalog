package com.autoxy.car_catalog.validator;

import com.autoxy.car_catalog.entity.CarEntity;

import java.time.Year;
import java.time.format.DateTimeParseException;

public class CarValidator {

    public static boolean isCarStatusValid(CarEntity entity) throws NullPointerException {
        return entity.getStatus().equals("available") || entity.getStatus().equals("sold");
    }

    public static void validateCarYear(CarEntity entity) throws DateTimeParseException, NullPointerException {
        Year.parse(entity.getYearOfProduction());
    }

}
