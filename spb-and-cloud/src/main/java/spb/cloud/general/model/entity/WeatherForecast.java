package spb.cloud.general.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
 @NoArgsConstructor
@AllArgsConstructor
public class WeatherForecast {
    private double latitude;
    private double longitude;
    private String timezone;
    private List<HourlyForecast> hourlyForecasts;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HourlyForecast {
        private LocalDateTime dateTime;
        private double temperature;
        private double apparentTemperature;
    }
}
