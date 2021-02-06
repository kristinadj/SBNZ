package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.Failure;

import java.util.List;

public interface FailureRepository extends JpaRepository<Failure, Long> {

    long countByDTCStartingWith(String dtc);

    List<Failure> findAllByIsManufacturerSpecific(Boolean isManufacturerSpecific);
}
