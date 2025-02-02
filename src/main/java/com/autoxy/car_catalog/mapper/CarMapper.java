package com.autoxy.car_catalog.mapper;

import com.autoxy.car_catalog.dto.CarRequestDto;
import com.autoxy.car_catalog.dto.CarResponseDto;
import com.autoxy.car_catalog.entity.CarEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
