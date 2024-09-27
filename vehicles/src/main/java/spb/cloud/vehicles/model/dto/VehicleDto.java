package spb.cloud.vehicles.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link spb.cloud.vehicles.model.entity.Vehicle}
 */
public record VehicleDto(Long id, String make, String model, Integer year, String color) implements Serializable {

    public VehicleDto(String make, String model, Integer year, String color) {
        this(null, make, model, year, color);
    }

}