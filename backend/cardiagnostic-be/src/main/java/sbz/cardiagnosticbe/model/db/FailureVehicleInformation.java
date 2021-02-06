package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;

@Entity
public class FailureVehicleInformation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;

    private Integer minVehicleProductionYear;

    private Integer maxVehicleProductionYear;

    public FailureVehicleInformation(Long id, VehicleModel vehicleModel, Integer minVehicleProductionYear, Integer maxVehicleProductionYear) {
        this.id = id;
        this.vehicleModel = vehicleModel;
        this.minVehicleProductionYear = minVehicleProductionYear;
        this.maxVehicleProductionYear = maxVehicleProductionYear;
    }

    public FailureVehicleInformation() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getMinVehicleProductionYear() {
        return minVehicleProductionYear;
    }

    public void setMinVehicleProductionYear(Integer minVehicleProductionYear) {
        this.minVehicleProductionYear = minVehicleProductionYear;
    }

    public Integer getMaxVehicleProductionYear() {
        return maxVehicleProductionYear;
    }

    public void setMaxVehicleProductionYear(Integer maxVehicleProductionYear) {
        this.maxVehicleProductionYear = maxVehicleProductionYear;
    }
}
