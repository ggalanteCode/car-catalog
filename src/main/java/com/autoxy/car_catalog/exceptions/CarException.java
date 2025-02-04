package com.autoxy.car_catalog.exceptions;

public abstract class CarException extends RuntimeException {

    public CarException(String message) {
        super(message);
    }
}
