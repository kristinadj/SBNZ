package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.db.Failure;

import java.util.List;

public class PossibleFailuresList {
    private List<PossibleFailure> failures;

    public PossibleFailuresList() {
    }

    public PossibleFailuresList(List<PossibleFailure> failures) {
        this.failures = failures;
    }

    public List<PossibleFailure> getFailures() {
        return failures;
    }

    public void setFailures(List<PossibleFailure> failures) {
        this.failures = failures;
    }
}
