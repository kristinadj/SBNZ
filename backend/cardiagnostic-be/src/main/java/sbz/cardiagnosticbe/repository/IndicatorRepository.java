package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.db.Indicator;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {

    Indicator findByDescription(String description);
}
