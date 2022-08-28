package com.example.MsweatherRequest.controller;

import com.example.MsweatherRequest.configuration.RabbitProducer;
import com.example.MsweatherRequest.mapper.WeatherRequestMapper;
import com.example.MsweatherRequest.model.WeatherRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    // create a weather request and sent it to the queue;

    @Autowired
    RabbitProducer rabbitProducer;

    @Autowired
    WeatherRequestMapper weatherRequestMapper;

    @PostMapping(value = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody WeatherRequest weatherRequest){
        log.info("a new request has been submitted and transfer to the queue {}", weatherRequest);
;

        rabbitProducer.SendToQueue(weatherRequestMapper.weatherRequestToDto(weatherRequest));

    }

}

