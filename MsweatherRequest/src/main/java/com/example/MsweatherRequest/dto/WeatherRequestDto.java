package com.example.MsweatherRequest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRequestDto {

        private String cityName;
        private Integer frequency;
        private Integer threshold;

    }
