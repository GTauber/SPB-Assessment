package spb.cloud.vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spb.cloud.vehicles.model.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
