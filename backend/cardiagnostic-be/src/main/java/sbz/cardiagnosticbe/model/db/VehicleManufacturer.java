package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;
import java.util.Set;

@Entity
public class VehicleManufacturer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<VehicleModel> vehicleModels;

    public VehicleManufacturer(Long id, String name, Set<VehicleModel> vehicleModels) {
        this.id = id;
        this.name = name;
        this.vehicleModels = vehicleModels;
    }

    public VehicleManufacturer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VehicleModel> getVehicleModels() {
        return vehicleModels;
    }

    public void setVehicleModels(Set<VehicleModel> vehicleModels) {
        this.vehicleModels = vehicleModels;
    }
}
