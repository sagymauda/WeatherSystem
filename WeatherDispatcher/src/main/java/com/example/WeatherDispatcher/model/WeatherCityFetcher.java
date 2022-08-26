package com.example.WeatherDispatcher.model;

import com.example.WeatherDispatcher.utils.TemperatureUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

@Data
@Slf4j

public class WeatherCityFetcher implements Runnable {

    private RestTemplate restTemplate;
    private String name;
    private Integer threshold;

    public WeatherCityFetcher(RestTemplate restTemplate, String name, Integer threshold) {
        this.restTemplate = restTemplate;
        this.name = name;
        this.threshold = threshold;
    }

    private Double oldTemp;

    @Override
    public void run() {
        ResponseEntity<WeatherResult> resultFromApi = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?q=" + name + "&appid=e735b6b632e6c008be941b8dbdb346d4" + "&units=metric", WeatherResult.class);

        Long timestamp = Instant.now().toEpochMilli();
        Double currentTemp = resultFromApi.getBody().getMain().getTemp();

        if (oldTemp != null && currentTemp != null && TemperatureUtil.checkDiff(oldTemp, currentTemp, threshold)) {
            log.info("At time {} The temperature  At {} is {} And Wind Speed of {} Be Careful The Weather has change Too Much", timestamp, resultFromApi.getBody().getName(), currentTemp, resultFromApi.getBody().getWind().getSpeed());
        } else {
            log.info("At time {} The temperature  At {} is {} And Wind Speed of {}", timestamp, resultFromApi.getBody().getName(), currentTemp, resultFromApi.getBody().getWind().getSpeed());
        }
        oldTemp = currentTemp;
    }

}

