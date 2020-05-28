package sbz.cardiagnosticbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Failure {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @ManyToMany
    private List<FailureIndicator> failureIndicators;

    @ManyToMany
    private List<Car> car; // if empty then applies to all cars

    private String repairSolution;

    private boolean isPartReplaceable;

}
