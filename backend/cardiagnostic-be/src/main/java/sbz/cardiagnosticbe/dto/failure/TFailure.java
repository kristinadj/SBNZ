package sbz.cardiagnosticbe.dto.failure;

import sbz.cardiagnosticbe.dto.detectedFailure.TRepairStep;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.enums.FailureSeverity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TFailure {
    private Long id;
    private String dtcCode;

    private String name;

    private boolean isManufacturerSpecific;

    private String vehicleManufacturer;

    private String vehicleModel;

    private int minVehicleProductionYear;

    private int maxVehicleProductionYear;

    private List<String> indicators;

    private List<TRepairStep> repairSteps;

    private FailureSeverity failureSeverity;

    public TFailure() {}

    public TFailure(Long id, String dtcCode, String name, boolean isManufacturerSpecific, String vehicleManufacturer, String vehicleModel, int minVehicleProductionYear, int maxVehicleProductionYear, List<String> indicators, List<TRepairStep> repairSteps, FailureSeverity failureSeverity) {
        this.id = id;
        this.dtcCode = dtcCode;
        this.name = name;
        this.isManufacturerSpecific = isManufacturerSpecific;
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModel = vehicleModel;
        this.minVehicleProductionYear = minVehicleProductionYear;
        this.maxVehicleProductionYear = maxVehicleProductionYear;
        this.indicators = indicators;
        this.repairSteps = repairSteps;
        this.failureSeverity = failureSeverity;
    }

    public TFailure(Failure failure) {
        this.id = id;
        this.dtcCode = failure.getDTC();
        this.name = failure.getFailureName();
        this.isManufacturerSpecific = failure.getManufacturerSpecific();

        if (this.isManufacturerSpecific) {
            this.vehicleManufacturer = failure.getVehicleInformation().getVehicleModel().getVehicleManufacturer().getName();
            this.vehicleModel = failure.getVehicleInformation().getVehicleModel().getModelName();
            this.minVehicleProductionYear = failure.getVehicleInformation().getMinVehicleProductionYear();
            this.maxVehicleProductionYear = failure.getVehicleInformation().getMaxVehicleProductionYear();
        }

        this.indicators = failure.getIndicators().stream().map
                (i -> i.getDescription()).collect(Collectors.toList());
        this.repairSteps = failure.getRepairSteps().stream().map
                (s -> new TRepairStep(s)).collect(Collectors.toList());
        Collections.sort(this.repairSteps);
        this.failureSeverity = failure.getFailureSeverity();
    }


    public String getDtcCode() {
        return dtcCode;
    }

    public void setDtcCode(String dtcCode) {
        this.dtcCode = dtcCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isManufacturerSpecific() {
        return isManufacturerSpecific;
    }

    public void setManufacturerSpecific(boolean manufacturerSpecific) {
        isManufacturerSpecific = manufacturerSpecific;
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

    public int getMinVehicleProductionYear() {
        return minVehicleProductionYear;
    }

    public void setMinVehicleProductionYear(int minVehicleProductionYear) {
        this.minVehicleProductionYear = minVehicleProductionYear;
    }

    public int getMaxVehicleProductionYear() {
        return maxVehicleProductionYear;
    }

    public void setMaxVehicleProductionYear(int maxVehicleProductionYear) {
        this.maxVehicleProductionYear = maxVehicleProductionYear;
    }

    public List<String> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<String> indicators) {
        this.indicators = indicators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TRepairStep> getRepairSteps() {
        return repairSteps;
    }

    public void setRepairSteps(List<TRepairStep> repairSteps) {
        this.repairSteps = repairSteps;
    }

    public FailureSeverity getFailureSeverity() {
        return failureSeverity;
    }

    public void setFailureSeverity(FailureSeverity failureSeverity) {
        this.failureSeverity = failureSeverity;
    }
}
