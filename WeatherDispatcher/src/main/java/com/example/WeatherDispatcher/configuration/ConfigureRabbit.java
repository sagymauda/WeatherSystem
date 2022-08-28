package com.example.WeatherDispatcher.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureRabbit {

    public final static String MESSAGE_QUEUE = "messages";

    @Bean
    public Queue defaultParsingQueue(){
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


}
