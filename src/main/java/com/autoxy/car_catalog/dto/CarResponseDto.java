package com.autoxy.car_catalog.dto;

public record CarResponseDto(Long id, String brand, String model, String yearOfProduction, Double price, String status) {}
