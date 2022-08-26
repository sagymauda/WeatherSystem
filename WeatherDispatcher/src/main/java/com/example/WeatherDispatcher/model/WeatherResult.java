package com.example.WeatherDispatcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResult {

    private String name;
    private Main main;
    private Wind wind;


    @Data
    class Main {
        @JsonProperty("temp")
        public Double temp;
    }

    @Data
    class Wind {
        @JsonProperty("speed")
        public Double speed;
    }
}
