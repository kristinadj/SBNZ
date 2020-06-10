package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.Indicator;
import sbz.cardiagnosticbe.model.enums.CarState;

import java.util.Set;

public class VisibleIndicators {
    Set<Indicator> indicators;

    CarState carState;

    public VisibleIndicators() {
    }

    public VisibleIndicators(Set<Indicator> indicators, CarState carState) {
        this.indicators = indicators;
        this.carState = carState;
    }

    public Set<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<Indicator> indicators) {
        this.indicators = indicators;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }
}
