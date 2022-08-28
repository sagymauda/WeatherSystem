package com.example.WeatherDispatcher.configuration;

import com.example.WeatherDispatcher.model.WeatherRequest;
import com.example.WeatherDispatcher.service.HandleMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class Consume {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.url}")
    private String url;


    @Autowired
    HandleMessageService handleMessageService;


    @RabbitListener(queues = {ConfigureRabbit.MESSAGE_QUEUE})
    public void getMessage(WeatherRequest weatherRequest) {
        log.info("Received message with city name{} and sending it to Weather Api", weatherRequest);

        handleMessageService.callWeatherApi(weatherRequest ,url,restTemplate);

    }
}
