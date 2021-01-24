package sbz.cardiagnosticbe.dto.failure;

import lombok.*;
import sbz.cardiagnosticbe.model.enums.VehiclePart;

import javax.validation.constraints.*;
import java.util.Set;

public class TFailure {

    @NotNull(message = "Must have vehicle type")
    public VehiclePart vehiclePart;

    public boolean isManufacturerSpecific;

    @Min(0)
    @Max(7)
    public int vehicleSubsystem;

    @Min(0)
    @Max(2)
    public int carState;

    @NotEmpty(message = "Indicators list can't be empty")
    public Set<String> indicators;

    @NotBlank(message = "Failure name can't be blank")
    public String failureName;

    @NotBlank(message = "Failure name can't be blank")
    public String repairSolution;

    public String dtcCode;

    public TFailure() {}

    public TFailure(@NotNull(message = "Must have vehicle type") VehiclePart vehiclePart, boolean isManufacturerSpecific, @Min(0) @Max(7) int vehicleSubsystem, @Min(0) @Max(2) int carState, @NotEmpty(message = "Indicators list can't be empty") Set<String> indicators, @NotBlank(message = "Failure name can't be blank") String failureName, @NotBlank(message = "Failure name can't be blank") String repairSolution, String dtcCode) {
        this.vehiclePart = vehiclePart;
        this.isManufacturerSpecific = isManufacturerSpecific;
        this.vehicleSubsystem = vehicleSubsystem;
        this.carState = carState;
        this.indicators = indicators;
        this.failureName = failureName;
        this.repairSolution = repairSolution;
        this.dtcCode = dtcCode;
    }

    public VehiclePart getVehiclePart() {
        return vehiclePart;
    }

    public void setVehiclePart(VehiclePart vehiclePart) {
        this.vehiclePart = vehiclePart;
    }

    public boolean isManufacturerSpecific() {
        return isManufacturerSpecific;
    }

    public void setManufacturerSpecific(boolean manufacturerSpecific) {
        isManufacturerSpecific = manufacturerSpecific;
    }

    public int getVehicleSubsystem() {
        return vehicleSubsystem;
    }

    public void setVehicleSubsystem(int vehicleSubsystem) {
        this.vehicleSubsystem = vehicleSubsystem;
    }

    public int getCarState() {
        return carState;
    }

    public void setCarState(int carState) {
        this.carState = carState;
    }

    public Set<String> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<String> indicators) {
        this.indicators = indicators;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public String getRepairSolution() {
        return repairSolution;
    }

    public void setRepairSolution(String repairSolution) {
        this.repairSolution = repairSolution;
    }

    public String getDtcCode() {
        return dtcCode;
    }

    public void setDtcCode(String dtcCode) {
        this.dtcCode = dtcCode;
    }
}
