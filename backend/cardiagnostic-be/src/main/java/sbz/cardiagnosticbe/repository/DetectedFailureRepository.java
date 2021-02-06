package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.DetectedFailure;
import sbz.cardiagnosticbe.model.db.Failure;

import java.util.List;

public interface DetectedFailureRepository extends JpaRepository<DetectedFailure, Long> {
    List<DetectedFailure> findAllByFailure(Failure failure);
}
