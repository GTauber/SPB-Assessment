package spb.cloud.general.service;

import spb.cloud.general.model.entity.WeatherForecast;
import spb.cloud.general.model.entity.WeatherForecast.HourlyForecast;
import spb.cloud.general.model.entity.WeatherResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;

    public Mono<WeatherForecast> getWeatherForecast(Double latitude, Double longitude, Integer forecastDays) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/forecast")
                .queryParam("latitude", latitude != null ? latitude : -22.9064)
                .queryParam("longitude", longitude != null ? longitude : -47.0616)
                .queryParam("hourly", "temperature_2m,apparent_temperature")
                .queryParam("forecast_days", forecastDays != null ? forecastDays : 1)
                .build())
            .retrieve()
            .bodyToMono(WeatherResponse.class)
            .map(this::convertToWeatherForecast);
    }

    private WeatherForecast convertToWeatherForecast(WeatherResponse response) {
        WeatherForecast forecast = new WeatherForecast();
        forecast.setLatitude(response.getLatitude());
        forecast.setLongitude(response.getLongitude());
        forecast.setTimezone(response.getTimezone());

        List<HourlyForecast> hourlyForecasts = IntStream.range(0, response.getHourly().getTime().size())
            .mapToObj(i -> {
                HourlyForecast hourly = new HourlyForecast();
                hourly.setDateTime(LocalDateTime.parse(response.getHourly().getTime().get(i)));
                hourly.setTemperature(response.getHourly().getTemperature2m().get(i));
                hourly.setApparentTemperature(response.getHourly().getApparentTemperature().get(i));
                return hourly;
            })
            .toList();

        forecast.setHourlyForecasts(hourlyForecasts);
        return forecast;
    }

}
