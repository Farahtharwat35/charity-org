package com.charity_org.demo.Mapper;

import com.charity_org.demo.DTO.PostOrPutEventRequest;
import com.charity_org.demo.Models.Event;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<PostOrPutEventRequest, Event>() {
            protected void configure() {
                map(source.getEventName(), destination.getEventName());
                map(source.getEventDate(), destination.getEventDate());
                map(source.getEventLocationId(), destination.getEventLocation());
                map(source.getDescription(), destination.getDescription());
                map(source.getEventStatus(), destination.getStatus());

            }
        });

        return modelMapper;
    }
}