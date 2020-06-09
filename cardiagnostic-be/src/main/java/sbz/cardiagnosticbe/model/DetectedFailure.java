package sbz.cardiagnosticbe.model;

import sbz.cardiagnosticbe.model.enums.FailureFrequency;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class DetectedFailure {

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

    public DetectedFailure() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public Set<Date> getDiagnoseHistory() {
        return diagnoseHistory;
    }

    public void setDiagnoseHistory(Set<Date> diagnoseHistory) {
        this.diagnoseHistory = diagnoseHistory;
    }

    public FailureFrequency getFailureFrequency() {
        return failureFrequency;
    }

    public void setFailureFrequency(FailureFrequency failureFrequency) {
        this.failureFrequency = failureFrequency;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }
}
