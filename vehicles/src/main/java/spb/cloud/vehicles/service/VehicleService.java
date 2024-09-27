package spb.cloud.vehicles.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spb.cloud.vehicles.model.entity.Vehicle;
import spb.cloud.vehicles.model.mapper.VehicleMapper;
import spb.cloud.vehicles.repository.VehicleRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public List<Vehicle> getAllVehicles() {
        log.info("Getting all vehicles");
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        log.info("Getting vehicle by id: {}", id);
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        log.info("Creating vehicle: {}", vehicle);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        return vehicleRepository.findById(id)
            .map(existingVehicle -> vehicleMapper.partialUpdate(vehicle, existingVehicle))
            .orElse(null);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

}
