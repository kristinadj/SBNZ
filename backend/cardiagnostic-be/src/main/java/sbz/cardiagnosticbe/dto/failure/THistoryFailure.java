package sbz.cardiagnosticbe.dto.failure;

import sbz.cardiagnosticbe.dto.detectedFailure.DetectionResultType;
import sbz.cardiagnosticbe.model.db.DetectedFailure;
import sbz.cardiagnosticbe.model.db.DetectedRelatedFailures;
import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.model.enums.FailureSeverity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class THistoryFailure implements Comparable<THistoryFailure> {
    private DetectionResultType type;
    private Long id;
    private List<String> failureNames;
    private List<String> indicators;
    private FailureSeverity failureSeverity;
    private String vehicleManufacturer;
    private String vehicleModel;
    private int vehicleProductionYear;
    private String appliedRepairStep;
    private LocalDateTime timestamp;

    public THistoryFailure(DetectionResultType type, Long id, List<String> failureNames, List<String> indicators, FailureSeverity failureSeverity, String vehicleManufacturer, String vehicleModel, int vehicleProductionYear, String appliedRepairStep, LocalDateTime timestamp) {
        this.type = type;
        this.id = id;
        this.failureNames = failureNames;
        this.indicators = indicators;
        this.failureSeverity = failureSeverity;
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModel = vehicleModel;
        this.vehicleProductionYear = vehicleProductionYear;
        this.appliedRepairStep = appliedRepairStep;
        this.timestamp = timestamp;
    }

    public THistoryFailure(DetectedFailure detectedFailure) {
        this.type = DetectionResultType.ONE_FAILURE;
        this.id = detectedFailure.getFailure().getId();
        this.failureNames = new ArrayList<>();
        this.failureNames.add(detectedFailure.getFailure().getDTC() + " - " + detectedFailure.getFailure().getFailureName());
        this.indicators = detectedFailure.getFailure().getIndicators().stream()
                .map(i -> i.getDescription()).collect(Collectors.toList());
        this.failureSeverity = detectedFailure.getFailure().getFailureSeverity();
        this.vehicleManufacturer = detectedFailure.getVehicleModel().getVehicleManufacturer().getName();
        this.vehicleModel = detectedFailure.getVehicleModel().getModelName();
        this.vehicleProductionYear = detectedFailure.getVehicleProductionYear();
        this.appliedRepairStep = detectedFailure.getRepairStepApplied().getDescription();
        this.timestamp = detectedFailure.getTimestamp();
    }

    public THistoryFailure(DetectedRelatedFailures detectedFailure) {
        this.type = DetectionResultType.RELATED_FAILURES;
        this.id = detectedFailure.getRelatedFailure().getId();
        this.failureNames = detectedFailure.getRelatedFailure().getFailures().stream().map
                (f -> f.getDTC() + " -  " + f.getFailureName()).collect(Collectors.toList());
        Set<Indicator> indicatorList = detectedFailure.getRelatedFailure().getFailures().stream().flatMap
                (f -> f.getIndicators().stream()).collect(Collectors.toSet());
        this.indicators = indicatorList.stream().map
                (i -> i.getDescription()).collect(Collectors.toList());
        this.vehicleManufacturer = detectedFailure.getVehicleModel().getVehicleManufacturer().getName();
        this.vehicleModel = detectedFailure.getVehicleModel().getModelName();
        this.vehicleProductionYear = detectedFailure.getVehicleProductionYear();
        this.appliedRepairStep = detectedFailure.getRelatedFailure().getRepairDescription();
        this.timestamp = detectedFailure.getTimestamp();
    }

    public DetectionResultType getType() {
        return type;
    }

    public void setType(DetectionResultType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getFailureNames() {
        return failureNames;
    }

    public void setFailureNames(List<String> failureNames) {
        this.failureNames = failureNames;
    }

    public List<String> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<String> indicators) {
        this.indicators = indicators;
    }

    public FailureSeverity getFailureSeverity() {
        return failureSeverity;
    }

    public void setFailureSeverity(FailureSeverity failureSeverity) {
        this.failureSeverity = failureSeverity;
    }

    public String getVehicleManufacturer() {
        return vehicleManufacturer;
    }

    public void setVehicleManufacturer(String vehicleManufacturer) {
        this.vehicleManufacturer = vehicleManufacturer;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public int getVehicleProductionYear() {
        return vehicleProductionYear;
    }

    public void setVehicleProductionYear(int vehicleProductionYear) {
        this.vehicleProductionYear = vehicleProductionYear;
    }

    public String getAppliedRepairStep() {
        return appliedRepairStep;
    }

    public void setAppliedRepairStep(String appliedRepairStep) {
        this.appliedRepairStep = appliedRepairStep;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(THistoryFailure o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }
}
