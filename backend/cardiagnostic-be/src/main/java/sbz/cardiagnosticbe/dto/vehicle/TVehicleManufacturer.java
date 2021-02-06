package sbz.cardiagnosticbe.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbz.cardiagnosticbe.model.db.VehicleManufacturer;
import sbz.cardiagnosticbe.model.db.VehicleModel;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TVehicleManufacturer {

    private Long id;

    @NotBlank(message = "Name can' be blank")
    private String name;

    private List<TVehicleModel> vehicleModels;


    public TVehicleManufacturer(VehicleManufacturer vehicleManufacturer, List<VehicleModel> vehicleModels) {
        this.id = vehicleManufacturer.getId();
        this.name = vehicleManufacturer.getName();
        this.vehicleModels = vehicleModels.stream().map
                (v -> new TVehicleModel(v)).collect(Collectors.toList());
    }
}
