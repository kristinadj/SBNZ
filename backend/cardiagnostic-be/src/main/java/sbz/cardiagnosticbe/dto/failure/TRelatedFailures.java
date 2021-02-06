package sbz.cardiagnosticbe.dto.failure;

import javax.validation.constraints.NotBlank;
import java.util.Set;


public class TRelatedFailures {

    private Set<Long> failuresIds;

    @NotBlank(message = "Repair description name can't be blank")
    private String repairDescription;

    public TRelatedFailures() {}

    public TRelatedFailures(Set<Long> failuresIds, @NotBlank(message = "Repair description name can't be blank") String repairDescription) {
        this.failuresIds = failuresIds;
        this.repairDescription = repairDescription;
    }

    public Set<Long> getFailuresIds() {
        return failuresIds;
    }

    public void setFailuresIds(Set<Long> failuresIds) {
        this.failuresIds = failuresIds;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }
}
