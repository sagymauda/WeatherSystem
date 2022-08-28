package com.example.MsweatherRequest.configuration;


import com.example.MsweatherRequest.dto.WeatherRequestDto;
import com.example.MsweatherRequest.model.WeatherRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void SendToQueue(WeatherRequestDto weatherRequestDto ) {
        log.info("sending  cityName {}", weatherRequestDto);
        rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_EXCHANGE, RabbitMqConfig.ROUTING_KEY , weatherRequestDto);
        log.info("sent was success");
    }
}
