package sbz.cardiagnosticbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority implements GrantedAuthority {

    @Id
    Long id;

    String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
