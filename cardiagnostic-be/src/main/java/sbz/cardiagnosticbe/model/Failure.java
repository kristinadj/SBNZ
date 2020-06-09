package sbz.cardiagnosticbe.model;

import sbz.cardiagnosticbe.model.enums.CarState;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Failure {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String DTC;

    @ManyToMany
    private Set<Symptom> symptoms;

    @Enumerated(EnumType.STRING)
    private CarState carState;

    private String failureName;

    private String repairSolution;

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

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public String getRepairSolution() {
        return repairSolution;
    }

    public void setRepairSolution(String repairSolution) {
        this.repairSolution = repairSolution;
    }

    @Override
    public String toString() {
        return "Failure{" +
                "id=" + id +
                ", DTC='" + DTC + '\'' +
                ", symptoms=" + symptoms +
                ", carState=" + carState +
                ", failureName='" + failureName + '\'' +
                ", repairSolution='" + repairSolution + '\'' +
                '}';
    }
}
