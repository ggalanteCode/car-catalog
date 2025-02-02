package com.autoxy.car_catalog.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfiguration {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

}
