package com.example.WeatherDispatcher.model;

import lombok.Data;

@Data
public class WeatherResult {

    private String name;
    private Main main;
    private Wind wind;


    @Data
    public class Main {
        private Double temp;
    }


    @Data
    public class Wind {
        private Double speed;
    }
}
