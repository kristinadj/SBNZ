package sbz.cardiagnosticbe.dto.failure;

import sbz.cardiagnosticbe.model.db.Failure;

public class TFailureBasicInfo {
    public Long id;

    public String dtcCodeFailureName;

    public TFailureBasicInfo() {}

    public TFailureBasicInfo(Long id, String dtcCodeFailureName) {
        this.id = id;
        this.dtcCodeFailureName = dtcCodeFailureName;
    }

    public TFailureBasicInfo(Failure failure) {
        this.id = failure.getId();
        this.dtcCodeFailureName = failure.getDTC() + " - " + failure.getFailureName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDtcCodeFailureName() {
        return dtcCodeFailureName;
    }

    public void setDtcCodeFailureName(String dtcCodeFailureName) {
        this.dtcCodeFailureName = dtcCodeFailureName;
    }
}
