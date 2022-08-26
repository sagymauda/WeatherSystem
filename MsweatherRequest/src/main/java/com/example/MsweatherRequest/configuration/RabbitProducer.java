package com.example.MsweatherRequest.configuration;


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

    public RabbitProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void SendToQueue(WeatherRequest weatherRequest ) {
        log.info("sending  cityName {}", weatherRequest);
        rabbitTemplate.convertAndSend(RabbitMqConfig.MESSAGE_EXCHANGE, RabbitMqConfig.ROUTING_KEY , weatherRequest);
        log.info("sent was success");
    }
}
