package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.RelatedFailures;

public interface RelatedFailuresRepository extends JpaRepository<RelatedFailures, Long> {
}
