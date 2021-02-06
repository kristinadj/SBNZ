package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.db.RelatedFailures;

import java.util.List;

public class DetectedRelatedFailuresProblems {
    private List<RelatedFailures> relatedFailuresProblems;

    public DetectedRelatedFailuresProblems()
    {}

    public DetectedRelatedFailuresProblems(List<RelatedFailures> relatedFailuresProblems) {
        this.relatedFailuresProblems = relatedFailuresProblems;
    }

    public List<RelatedFailures> getRelatedFailuresProblems() {
        return relatedFailuresProblems;
    }

    public void setRelatedFailuresProblems(List<RelatedFailures> relatedFailuresProblems) {
        this.relatedFailuresProblems = relatedFailuresProblems;
    }
}
