package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.FailureVehicleInformation;

public interface FailureVehicleInformationRepository extends JpaRepository<FailureVehicleInformation, Long> {
}
