package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.Symptom;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    Symptom findBySymptomName(String symptomName);
}
