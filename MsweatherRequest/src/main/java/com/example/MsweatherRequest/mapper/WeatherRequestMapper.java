package com.example.MsweatherRequest.mapper;

import com.example.MsweatherRequest.dto.WeatherRequestDto;
import com.example.MsweatherRequest.model.WeatherRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class WeatherRequestMapper {

    @Autowired
    ModelMapper mapper;

    public WeatherRequestDto weatherRequestToDto(WeatherRequest weatherRequest ){
      return   mapper.map(weatherRequest,WeatherRequestDto.class);
    }


}
