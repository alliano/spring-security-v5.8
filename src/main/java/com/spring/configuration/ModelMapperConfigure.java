package com.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eye2web.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfigure {
   
   @Bean(name = "modelMapper")
   public ModelMapper modelMapper(){
      return new ModelMapper();
   }
}
