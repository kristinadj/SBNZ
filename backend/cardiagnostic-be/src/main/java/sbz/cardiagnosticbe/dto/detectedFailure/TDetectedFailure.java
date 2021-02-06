package sbz.cardiagnosticbe.dto.detectedFailure;

import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.db.RepairStep;
import sbz.cardiagnosticbe.model.enums.FailureSeverity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TDetectedFailure extends TDetectionResult {
    private Long id;

    private String failureName;

    private Set<String> indicators = new HashSet<>();

    private FailureSeverity failureSeverity;

    private TRepairStep repairStep;

    public TDetectedFailure() {}

    public TDetectedFailure(Long id, String failureName, Set<String> indicators, FailureSeverity failureSeverity, TRepairStep repairStep) {
        this.id = id;
        this.failureName = failureName;
        this.indicators = indicators;
        this.failureSeverity = failureSeverity;
        this.repairStep = repairStep;
    }

    public TDetectedFailure(Failure failure, RepairStep repairStep) {
        this.type = DetectionResultType.ONE_FAILURE;
        this.id = failure.getId();
        this.failureName = failure.getDTC() + " - " + failure.getFailureName();
        this.failureSeverity = failure.getFailureSeverity();
        this.indicators = failure.getIndicators().stream().map
                (i -> i.getDescription()).collect(Collectors.toSet());
        this.repairStep = new TRepairStep(repairStep);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFailureName() {
        return failureName;
    }

    public void setFailureName(String failureName) {
        this.failureName = failureName;
    }

    public Set<String> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<String> indicators) {
        this.indicators = indicators;
    }

    public FailureSeverity getFailureSeverity() {
        return failureSeverity;
    }

    public void setFailureSeverity(FailureSeverity failureSeverity) {
        this.failureSeverity = failureSeverity;
    }

    public TRepairStep getRepairStep() {
        return repairStep;
    }

    public void setRepairStep(TRepairStep repairStep) {
        this.repairStep = repairStep;
    }
}
