package sbz.cardiagnosticbe.dto;

import sbz.cardiagnosticbe.model.enums.VehiclePart;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TDtcParams {

    @NotNull(message = "Must have vehicle type")
    public VehiclePart vehiclePart;

    public boolean isManufacturerSpecific;

    @Min(0)
    @Max(7)
    public int vehicleSubsystem;

    public TDtcParams() {
    }

    public TDtcParams(@NotNull(message = "Must have vehicle type") VehiclePart vehiclePart, boolean isManufacturerSpecific, @Min(0) @Max(7) int vehicleSubsystem) {
        this.vehiclePart = vehiclePart;
        this.isManufacturerSpecific = isManufacturerSpecific;
        this.vehicleSubsystem = vehicleSubsystem;
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
}
