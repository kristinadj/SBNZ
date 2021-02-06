package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.VehicleManufacturer;

public interface VehicleManufacturerRepository extends JpaRepository<VehicleManufacturer, Long> {
    VehicleManufacturer findByName(String name);
}
