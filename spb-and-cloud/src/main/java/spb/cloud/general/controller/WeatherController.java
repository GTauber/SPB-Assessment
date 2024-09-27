package spb.cloud.general.controller;

import spb.cloud.general.model.entity.WeatherForecast;
import spb.cloud.general.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast")
    public Mono<WeatherForecast> getWeatherForecast(
        @RequestParam(required = false) Double latitude,
        @RequestParam(required = false) Double longitude,
        @RequestParam(required = false) Integer forecastDays) {
        return weatherService.getWeatherForecast(latitude, longitude, forecastDays);
    }
}
