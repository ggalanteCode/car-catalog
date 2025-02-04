package com.autoxy.car_catalog.dto;

public class CarErrorResponseDto {

    private String message;

    public CarErrorResponseDto(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
