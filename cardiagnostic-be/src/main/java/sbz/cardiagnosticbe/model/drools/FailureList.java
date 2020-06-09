package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.Failure;

import java.util.List;

public class FailureList {
    private List<Failure> failures;

    public FailureList() {
    }

    public FailureList(List<Failure> failures) {
        this.failures = failures;
    }

    public List<Failure> getFailures() {
        return failures;
    }

    public void setFailures(List<Failure> failures) {
        this.failures = failures;
    }
}
