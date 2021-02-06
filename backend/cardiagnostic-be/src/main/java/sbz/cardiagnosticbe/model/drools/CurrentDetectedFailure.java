package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.db.Failure;

public class CurrentDetectedFailure {
    private Failure failure;
    private int nextRepairStep;

    public CurrentDetectedFailure() {

    }

    public CurrentDetectedFailure(Failure failure, int nextRepairStep) {
        this.failure = failure;
        this.nextRepairStep = nextRepairStep;
    }

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public int getNextRepairStep() {
        return nextRepairStep;
    }

    public void setNextRepairStep(int nextRepairStep) {
        this.nextRepairStep = nextRepairStep;
    }
}
