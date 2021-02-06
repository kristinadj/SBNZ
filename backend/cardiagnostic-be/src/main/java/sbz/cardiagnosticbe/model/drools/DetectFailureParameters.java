package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.db.Indicator;

import java.util.Set;

public class DetectFailureParameters {
    Set<Indicator> indicators;
    long vehicleModelId;
    int vehicleProductionYear;

    public DetectFailureParameters() {
    }

    public DetectFailureParameters(Set<Indicator> indicators, long vehicleModelId, int vehicleProductionYear) {
        this.indicators = indicators;
        this.vehicleModelId = vehicleModelId;
        this.vehicleProductionYear = vehicleProductionYear;
    }

    public Set<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<Indicator> indicators) {
        this.indicators = indicators;
    }

    public long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public int getVehicleProductionYear() {
        return vehicleProductionYear;
    }

    public void setVehicleProductionYear(int vehicleProductionYear) {
        this.vehicleProductionYear = vehicleProductionYear;
    }
}
