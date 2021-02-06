package sbz.cardiagnosticbe.model.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DetectedFailure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;

    private Integer vehicleProductionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "failure_id")
    private Failure failure;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_step_id")
    private RepairStep repairStepApplied;

    public DetectedFailure(Long id, VehicleModel vehicleModel, Integer vehicleProductionYear, Failure failure, LocalDateTime timestamp, RepairStep repairStepApplied) {
        this.id = id;
        this.vehicleModel = vehicleModel;
        this.vehicleProductionYear = vehicleProductionYear;
        this.failure = failure;
        this.timestamp = timestamp;
        this.repairStepApplied = repairStepApplied;
    }
    public DetectedFailure() {

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

    public Failure getFailure() {
        return failure;
    }

    public void setFailure(Failure failure) {
        this.failure = failure;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public RepairStep getRepairStepApplied() {
        return repairStepApplied;
    }

    public void setRepairStepApplied(RepairStep repairStepApplied) {
        this.repairStepApplied = repairStepApplied;
    }
}
