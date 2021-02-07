package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.db.Failure;

public class CurrentDetectedFailure {
    private Failure failure;
    private int nextRepairStep;
    private Long vehicleModelId;
    private int vehicleProductionYear;

    public CurrentDetectedFailure() {

    }

    public CurrentDetectedFailure(Failure failure, int nextRepairStep, Long vehicleModelId, int vehicleProductionYear) {
        this.failure = failure;
        this.nextRepairStep = nextRepairStep;
        this.vehicleModelId = vehicleModelId;
        this.vehicleProductionYear = vehicleProductionYear;
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

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public int getVehicleProductionYear() {
        return vehicleProductionYear;
    }

    public void setVehicleProductionYear(int vehicleProductionYear) {
        this.vehicleProductionYear = vehicleProductionYear;
    }
}
