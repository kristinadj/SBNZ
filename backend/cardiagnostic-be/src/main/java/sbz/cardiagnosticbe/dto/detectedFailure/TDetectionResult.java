package sbz.cardiagnosticbe.dto.detectedFailure;

public abstract class TDetectionResult {
    protected DetectionResultType type;

    public DetectionResultType getType() {
        return type;
    }

    public void setType(DetectionResultType type) {
        this.type = type;
    }
}
