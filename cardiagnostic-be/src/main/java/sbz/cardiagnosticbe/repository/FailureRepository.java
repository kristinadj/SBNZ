package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.Failure;
public interface FailureRepository extends JpaRepository<Failure, Long> {

    long countByDTCStartingWith(String dtc);
}
