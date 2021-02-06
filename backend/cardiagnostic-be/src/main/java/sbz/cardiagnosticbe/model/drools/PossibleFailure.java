package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.db.Failure;

public class PossibleFailure {
    private Failure failure;
    private int matchingIndicatorsNumber;

    public PossibleFailure(Failure failure, int matchingIndicatorsNumber) {
        this.failure = failure;
        this.matchingIndicatorsNumber = matchingIndicatorsNumber;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public int getMatchingIndicatorsNumber() {
        return matchingIndicatorsNumber;
    }

    public void setMatchingIndicatorsNumber(int matchingIndicatorsNumber) {
        this.matchingIndicatorsNumber = matchingIndicatorsNumber;
    }
}
