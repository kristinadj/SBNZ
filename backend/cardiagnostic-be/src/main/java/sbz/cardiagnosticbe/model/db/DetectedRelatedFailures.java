package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DetectedRelatedFailures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;

    private Integer vehicleProductionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "related_failure_id")
    private RelatedFailures relatedFailure;

    private LocalDateTime timestamp;

    public DetectedRelatedFailures() {

    }

    public DetectedRelatedFailures(Long id, VehicleModel vehicleModel, Integer vehicleProductionYear, RelatedFailures relatedfailure, LocalDateTime timestamp) {
        this.id = id;
        this.vehicleModel = vehicleModel;
        this.vehicleProductionYear = vehicleProductionYear;
        this.relatedFailure = relatedfailure;
        this.timestamp = timestamp;
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

    public Integer getVehicleProductionYear() {
        return vehicleProductionYear;
    }

    public void setVehicleProductionYear(Integer vehicleProductionYear) {
        this.vehicleProductionYear = vehicleProductionYear;
    }

    public RelatedFailures getRelatedFailure() {
        return relatedFailure;
    }

    public void setRelatedFailure(RelatedFailures relatedfailure) {
        this.relatedFailure = relatedfailure;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
