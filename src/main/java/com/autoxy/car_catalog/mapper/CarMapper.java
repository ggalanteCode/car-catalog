package com.autoxy.car_catalog.mapper;

import com.autoxy.car_catalog.dto.*;
import com.autoxy.car_catalog.entity.CarEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CarMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CarEntity requestToEntity(CarRequestDto request) {
        return modelMapper.typeMap(CarRequestDto.class, CarEntity.class)
                .addMappings(mapper -> mapper.skip(CarEntity::setId))
                .map(request);
    }

    public CarResponseDto entityToResponse(CarEntity entity) {
         return modelMapper.map(entity, CarResponseDto.class);
    }

    public List<CarResponseDto> entitiesToResponses(List<CarEntity> entities) {
        List<CarResponseDto> responses = new ArrayList<>();
        entities.forEach(entity -> responses.add(modelMapper.map(entity, CarResponseDto.class)));
        return responses;
    }

    public void updateEntity(CarEntity entityWithNewValues, CarEntity entityToBeUpdated) {
        modelMapper.map(entityWithNewValues, entityToBeUpdated);
    }

}
