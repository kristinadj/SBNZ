package sbz.cardiagnosticbe.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sbz.cardiagnosticbe.model.db.VehicleModel;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TVehicleModel {

    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long vehicleManufacturerId;

    @NotBlank(message = "Name can' be blank")
    private String name;

    public TVehicleModel(VehicleModel vehicleModel) {
        this.id = vehicleModel.getId();
        this.name = vehicleModel.getModelName();
    }
}
