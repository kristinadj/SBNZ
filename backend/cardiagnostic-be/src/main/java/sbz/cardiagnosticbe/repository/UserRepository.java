package sbz.cardiagnosticbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbz.cardiagnosticbe.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username );
}
