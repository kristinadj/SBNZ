package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.db.RelatedFailures;

import java.util.List;

public interface RelatedFailuresRepository extends JpaRepository<RelatedFailures, Long> {
    List<RelatedFailures> findAllByFailuresContains(Failure failure);
}
