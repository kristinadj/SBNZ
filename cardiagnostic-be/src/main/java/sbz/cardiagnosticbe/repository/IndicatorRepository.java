package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.Indicator;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {

    Indicator findByIndicatorName(String indicatorName);
}
