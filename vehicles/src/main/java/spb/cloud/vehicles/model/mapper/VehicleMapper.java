package spb.cloud.vehicles.model.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import spb.cloud.vehicles.model.dto.VehicleDto;
import spb.cloud.vehicles.model.entity.Vehicle;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface VehicleMapper {

    Vehicle toEntity(VehicleDto vehicleDto);

    VehicleDto toDto(Vehicle vehicle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vehicle partialUpdate(
        Vehicle updatedVehicle, @MappingTarget Vehicle targetVehicle);
}