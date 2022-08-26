package com.example.WeatherDispatcher.configuration;

import com.example.WeatherDispatcher.model.WeatherCityFetcher;
import com.example.WeatherDispatcher.model.WeatherRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class Consume {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    RestTemplate restTemplate  =new RestTemplate();
    @RabbitListener(queues ={ConfigureRabbit.MESSAGE_QUEUE})
    public void getMessage(WeatherRequest weatherRequest ){
        log.info("Received message with city name{} and sending it to Weather Api",weatherRequest);

        taskScheduler.scheduleWithFixedDelay(new WeatherCityFetcher(restTemplate,weatherRequest.getCityName(),weatherRequest.getThreshold()),weatherRequest.getFrequency()*1000);

    }
}
