package sbz.cardiagnosticbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FailureIndicator {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String failureIndicator;
}
