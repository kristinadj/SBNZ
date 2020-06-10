package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.Indicator;
import sbz.cardiagnosticbe.model.enums.CarState;

import java.util.List;

public class VisibleIndicators {
    List<Indicator> indicators;

    CarState carState;

    public VisibleIndicators() {
    }

    public VisibleIndicators(List<Indicator> indicators, CarState carState) {
        this.indicators = indicators;
        this.carState = carState;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }
}
