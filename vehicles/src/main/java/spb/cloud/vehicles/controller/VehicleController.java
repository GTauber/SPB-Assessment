package spb.cloud.vehicles.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spb.cloud.vehicles.model.dto.VehicleDto;
import spb.cloud.vehicles.model.entity.Vehicle;
import spb.cloud.vehicles.model.mapper.VehicleMapper;
import spb.cloud.vehicles.service.VehicleService;

@RestController
@RequestMapping("/v1/vehicle")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return vehicle != null ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.createVehicle(vehicle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleMapper.toEntity(vehicleDto));
        return updatedVehicle != null ? ResponseEntity.ok(updatedVehicle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

}
