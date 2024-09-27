package spb.cloud.general.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {

    private double latitude;
    private double longitude;
    @JsonProperty("generationtime_ms")
    private double generationTimeMs;
    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;
    private String timezone;
    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;
    private double elevation;
    @JsonProperty("hourly_units")
    private HourlyUnits hourlyUnits;
    private Hourly hourly;

    @Data
    @Builder
    public static class HourlyUnits {
        private String time;
        @JsonProperty("temperature_2m")
        private String temperature2m;
        @JsonProperty("apparent_temperature")
        private String apparentTemperature;
    }

    @Data
    @Builder
    public static class Hourly {
        private List<String> time;
        @JsonProperty("temperature_2m")
        private List<Double> temperature2m;
        @JsonProperty("apparent_temperature")
        private List<Double> apparentTemperature;

    }

}
