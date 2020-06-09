package sbz.cardiagnosticbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetectedFailure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Failure failure;

    @ElementCollection
    @Temporal(TemporalType.TIMESTAMP)
    private Set<Date> diagnoseHistory;

    @Enumerated(EnumType.STRING)
    private FailureFrequency failureFrequency;

    private boolean isResolved; // failure was long time ago or resolved by mechanic
}
