package com.example.WeatherDispatcher.service;

import com.example.WeatherDispatcher.model.WeatherRequest;
import com.example.WeatherDispatcher.model.WeatherResult;
import com.example.WeatherDispatcher.utils.TemperatureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Slf4j
@Service
public class HandleMessageService {



    public void callWeatherApi(WeatherRequest weatherRequest, String url, RestTemplate restTemplate) {


        Double oldTemp = 0.0;

        ResponseEntity<WeatherResult> resultFromApi = restTemplate.getForEntity(url +"/data/2.5/weather?q=" + weatherRequest.getCityName() + "&appid=e735b6b632e6c008be941b8dbdb346d4" + "&units=metric", WeatherResult.class);

        resultFromApi.getBody().getMain();
        Long timestamp = Instant.now().toEpochMilli();
        Double currentTemp = resultFromApi.getBody().getMain().getTemp();


        if (oldTemp != null && currentTemp != null && TemperatureUtil.checkTemperatureDiff(oldTemp, currentTemp, weatherRequest.getThreshold())) {
            log.info("At time {} The temperature  At {} is {} And Wind Speed of {} Be Careful The Weather has change Too Much", timestamp, resultFromApi.getBody().getName(), currentTemp, resultFromApi.getBody().getWind().getSpeed());
        } else {
            log.info("At time {} The temperature  At {} is {} And Wind Speed of {}", timestamp, resultFromApi.getBody().getName(), currentTemp, resultFromApi.getBody().getWind().getSpeed());
        }
        oldTemp = currentTemp;
    }
}

