package sbz.cardiagnosticbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbz.cardiagnosticbe.model.VehiclePart;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TFailure {

    @NotNull(message = "Must have vehicle type")
    public VehiclePart vehiclePart;

    public Boolean isManufacturerSpecific;

    @PositiveOrZero(message = "Invalid vehicle subsystem")
    public Integer vehicleSubsystem;

    @NotBlank(message = "Failure name can't be blank")
    private String failureName;

    @NotBlank(message = "Failure name can't be blank")
    private String repairSolution;

    public String dtcCode;
}
