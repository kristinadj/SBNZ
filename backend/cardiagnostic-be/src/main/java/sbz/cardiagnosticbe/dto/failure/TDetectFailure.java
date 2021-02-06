package sbz.cardiagnosticbe.dto.failure;

import java.util.List;

public class TDetectFailure {
    public List<Long> indicatorsIds;

    public Long vehicleModelId;

    public int vehicleProductionYear;

    public TDetectFailure() {}

    public TDetectFailure(List<Long> indicatorsIds, Long vehicleModelId, int vehicleProductionYear) {
        this.indicatorsIds = indicatorsIds;
        this.vehicleModelId = vehicleModelId;
        this.vehicleProductionYear = vehicleProductionYear;
    }


    public List<Long> getIndicatorsIds() {
        return indicatorsIds;
    }

    public void setIndicatorsIds(List<Long> indicatorsIds) {
        this.indicatorsIds = indicatorsIds;
    }

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public int getVehicleProductionYear() {
        return vehicleProductionYear;
    }

    public void setVehicleProductionYear(int vehicleProductionYear) {
        this.vehicleProductionYear = vehicleProductionYear;
    }
}
