package com.example.MsweatherRequest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRequest {


    private Long cityId;
    private String cityName;
    private Integer frequency;
    private Integer threshold;

    }