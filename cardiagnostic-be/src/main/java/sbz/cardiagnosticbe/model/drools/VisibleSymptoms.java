package sbz.cardiagnosticbe.model.drools;

import sbz.cardiagnosticbe.model.Symptom;
import sbz.cardiagnosticbe.model.enums.CarState;

import java.util.List;

public class VisibleSymptoms {
    List<Symptom> symptoms;

    CarState carState;

    public VisibleSymptoms() {
    }

    public VisibleSymptoms(List<Symptom> symptoms, CarState carState) {
        this.symptoms = symptoms;
        this.carState = carState;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public CarState getCarState() {
        return carState;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }
}
