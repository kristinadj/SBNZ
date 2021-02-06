package sbz.cardiagnosticbe.model.db;

import sbz.cardiagnosticbe.model.enums.FailureSeverity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Failure {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String DTC;

    private String failureName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Indicator> indicators = new HashSet<>();

    @OneToMany(mappedBy = "failure", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<RepairStep> repairSteps  = new HashSet<>();

    private Boolean isManufacturerSpecific;

    @OneToOne
    @JoinColumn(name = "vehicle_information_id")
    private FailureVehicleInformation vehicleInformation;

    @Enumerated(EnumType.STRING)
    private FailureSeverity failureSeverity;

    public Failure(Long id, String DTC, String failureName, Set<Indicator> indicators, Set<RepairStep> repairSteps, Boolean isManufacturerSpecific, FailureVehicleInformation vehicleInformation, FailureSeverity failureSeverity) {
        this.id = id;
        this.DTC = DTC;
        this.failureName = failureName;
        this.indicators = indicators;
        this.repairSteps = repairSteps;
        this.isManufacturerSpecific = isManufacturerSpecific;
        this.vehicleInformation = vehicleInformation;
        this.failureSeverity = failureSeverity;
    }

    public Failure() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDTC() {
        return DTC;
    }

    public void setDTC(String DTC) {
        this.DTC = DTC;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public Set<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<Indicator> indicators) {
        this.indicators = indicators;
    }

    public Set<RepairStep> getRepairSteps() {
        return repairSteps;
    }

    public void setRepairSteps(Set<RepairStep> repairSteps) {
        this.repairSteps = repairSteps;
    }

    public Boolean getManufacturerSpecific() {
        return isManufacturerSpecific;
    }

    public void setManufacturerSpecific(Boolean manufactureSpecific) {
        isManufacturerSpecific = manufactureSpecific;
    }

    public FailureVehicleInformation getVehicleInformation() {
        return vehicleInformation;
    }

    public void setVehicleInformation(FailureVehicleInformation vehicleInformation) {
        this.vehicleInformation = vehicleInformation;
    }

    public FailureSeverity getFailureSeverity() {
        return failureSeverity;
    }

    public void setFailureSeverity(FailureSeverity failureSeverity) {
        this.failureSeverity = failureSeverity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Failure failure = (Failure) o;
        return id.equals(failure.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
