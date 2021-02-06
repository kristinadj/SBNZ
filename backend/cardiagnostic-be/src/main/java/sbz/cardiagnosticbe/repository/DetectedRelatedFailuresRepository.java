package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.DetectedRelatedFailures;
import sbz.cardiagnosticbe.model.db.RelatedFailures;

import java.util.List;

public interface DetectedRelatedFailuresRepository extends JpaRepository<DetectedRelatedFailures, Long>  {
    List<DetectedRelatedFailures> findAllByRelatedFailure(RelatedFailures relatedFailures);
}
