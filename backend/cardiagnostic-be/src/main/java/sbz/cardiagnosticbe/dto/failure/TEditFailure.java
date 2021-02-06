package sbz.cardiagnosticbe.dto.failure;

import sbz.cardiagnosticbe.model.enums.FailureSeverity;

import javax.validation.constraints.*;
import java.util.List;

public class TEditFailure {
    @NotBlank(message = "Failure name can't be blank")
    public String failureName;

    public boolean isManufacturerSpecific;

    @Null
    public long vehicleModelId;

    @Null
    public int minVehicleProductionYear;

    @Null
    public int maxVehicleProductionYear;

    @NotEmpty(message = "Indicators list can't be empty")
    public List<String> indicators;

    @NotEmpty(message = "RepairSteps list can't be empty")
    public List<String> repairSteps;

    @NotNull(message = "Must set failure severity")
    private FailureSeverity failureSeverity;

    public TEditFailure(@NotBlank(message = "Failure name can't be blank") String failureName, boolean isManufacturerSpecific, @Null long vehicleModelId, @Null int minVehicleProductionYear, @Null int maxVehicleProductionYear, @NotEmpty(message = "Indicators list can't be empty") List<String> indicators, @NotEmpty(message = "RepairSteps list can't be empty") List<String> repairSteps, @NotNull(message = "Must set failure severity") FailureSeverity failureSeverity) {
        this.failureName = failureName;
        this.isManufacturerSpecific = isManufacturerSpecific;
        this.vehicleModelId = vehicleModelId;
        this.minVehicleProductionYear = minVehicleProductionYear;
        this.maxVehicleProductionYear = maxVehicleProductionYear;
        this.indicators = indicators;
        this.repairSteps = repairSteps;
        this.failureSeverity = failureSeverity;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public boolean isManufacturerSpecific() {
        return isManufacturerSpecific;
    }

    public void setManufacturerSpecific(boolean manufacturerSpecific) {
        isManufacturerSpecific = manufacturerSpecific;
    }

    public long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public int getMinVehicleProductionYear() {
        return minVehicleProductionYear;
    }

    public void setMinVehicleProductionYear(int minVehicleProductionYear) {
        this.minVehicleProductionYear = minVehicleProductionYear;
    }

    public int getMaxVehicleProductionYear() {
        return maxVehicleProductionYear;
    }

    public void setMaxVehicleProductionYear(int maxVehicleProductionYear) {
        this.maxVehicleProductionYear = maxVehicleProductionYear;
    }

    public List<String> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<String> indicators) {
        this.indicators = indicators;
    }

    public List<String> getRepairSteps() {
        return repairSteps;
    }

    public void setRepairSteps(List<String> repairSteps) {
        this.repairSteps = repairSteps;
    }

    public FailureSeverity getFailureSeverity() {
        return failureSeverity;
    }

    public void setFailureSeverity(FailureSeverity failureSeverity) {
        this.failureSeverity = failureSeverity;
    }
}
