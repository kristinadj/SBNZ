package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;

@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_manufacturer_id")
    private VehicleManufacturer vehicleManufacturer;

    private String modelName;

    public VehicleModel(Long id, VehicleManufacturer vehicleManufacturer, String modelName) {
        this.id = id;
        this.vehicleManufacturer = vehicleManufacturer;
        this.modelName = modelName;
    }

    public VehicleModel() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleManufacturer getVehicleManufacturer() {
        return vehicleManufacturer;
    }

    public void setVehicleManufacturer(VehicleManufacturer vehicleManufacturer) {
        this.vehicleManufacturer = vehicleManufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
