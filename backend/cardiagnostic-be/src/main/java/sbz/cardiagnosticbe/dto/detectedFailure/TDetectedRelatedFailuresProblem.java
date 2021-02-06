package sbz.cardiagnosticbe.dto.detectedFailure;

import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.model.db.RelatedFailures;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TDetectedRelatedFailuresProblem extends TDetectionResult {

    private Long id;

    private Set<String> relatedFailuresNames = new HashSet<>();

    private Set<String> indicators = new HashSet<>();

    private String repairDescription;

    public TDetectedRelatedFailuresProblem() {}

    public TDetectedRelatedFailuresProblem(Long id, Set<String> relatedFailuresNames, Set<String> indicators, String repairDescription) {
        this.id = id;
        this.relatedFailuresNames = relatedFailuresNames;
        this.indicators = indicators;
        this.repairDescription = repairDescription;
    }

    public TDetectedRelatedFailuresProblem(RelatedFailures relatedFailures) {
        this.type = DetectionResultType.RELATED_FAILURES;
        this.id = relatedFailures.getId();
        this.relatedFailuresNames = relatedFailures.getFailures().stream().map
                (f -> f.getDTC() + " -  " + f.getFailureName()).collect(Collectors.toSet());
        Set<Indicator> indicatorList = relatedFailures.getFailures().stream().flatMap
                (f -> f.getIndicators().stream()).collect(Collectors.toSet());
        this.indicators = indicatorList.stream().map
                (i -> i.getDescription()).collect(Collectors.toSet());
        this.repairDescription = relatedFailures.getRepairDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getRelatedFailuresNames() {
        return relatedFailuresNames;
    }

    public void setRelatedFailuresNames(Set<String> relatedFailuresNames) {
        this.relatedFailuresNames = relatedFailuresNames;
    }

    public Set<String> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<String> indicators) {
        this.indicators = indicators;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }
}
