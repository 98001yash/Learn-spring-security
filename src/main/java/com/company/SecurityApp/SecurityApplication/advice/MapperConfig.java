package com.company.SecurityApp.SecurityApplication.advice;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class MapperConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    /**
     * @Bean
     * ModelMapper getModelMapper() {
     *     return new ModelMapper();
     *
     * }
     */


}
