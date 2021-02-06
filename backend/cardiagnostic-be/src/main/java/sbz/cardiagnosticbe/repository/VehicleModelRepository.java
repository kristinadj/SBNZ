package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.VehicleModel;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

    List<VehicleModel> findByVehicleManufacturerId(Long vehicleManufacturer_id);

    VehicleModel findByVehicleManufacturerIdAndModelName(Long vehicleManufacturer_id, String modelName);
}
