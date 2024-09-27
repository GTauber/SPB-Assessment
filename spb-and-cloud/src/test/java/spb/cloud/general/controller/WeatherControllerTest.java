package spb.cloud.general.controller;

import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import spb.cloud.general.model.entity.WeatherForecast;
import spb.cloud.general.service.WeatherService;

@WebFluxTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private WeatherService weatherService;

    @Test
    void getWeatherForecastSuccessfully() {
        var forecast = new WeatherForecast();
        var hourlyForcast = new WeatherForecast.HourlyForecast();
        hourlyForcast.setTemperature(25.0);
        hourlyForcast.setApparentTemperature(26.0);
        forecast.setHourlyForecasts(List.of(hourlyForcast));

        when(weatherService.getWeatherForecast(40.7128, -74.0060, 5)).thenReturn(Mono.just(forecast));

        webTestClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/weather/forecast")
                .queryParam("latitude", 40.7128)
                .queryParam("longitude", -74.0060)
                .queryParam("forecastDays", 5)
                .build())
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.hourlyForecasts[0].temperature").isEqualTo(25.0)
            .jsonPath("$.hourlyForecasts[0].apparentTemperature").isEqualTo(26.0);
    }

    @Test
    void getWeatherForecastWithoutParams() {
        var forecast = new WeatherForecast();
        var hourlyForcast = new WeatherForecast.HourlyForecast();
        hourlyForcast.setTemperature(25.0);
        hourlyForcast.setApparentTemperature(26.0);
        forecast.setHourlyForecasts(List.of(hourlyForcast));

        when(weatherService.getWeatherForecast(null, null, null)).thenReturn(Mono.just(forecast));

        webTestClient.get()
            .uri("/api/weather/forecast")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.hourlyForecasts[0].temperature").isEqualTo(25.0)
            .jsonPath("$.hourlyForecasts[0].apparentTemperature").isEqualTo(26.0);
    }

}