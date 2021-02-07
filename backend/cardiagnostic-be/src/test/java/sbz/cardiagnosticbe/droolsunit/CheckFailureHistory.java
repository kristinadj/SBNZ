package sbz.cardiagnosticbe.droolsunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import sbz.cardiagnosticbe.model.db.*;
import sbz.cardiagnosticbe.model.drools.CurrentDetectedFailure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class CheckFailureHistory {
    private static KieContainer kieContainer;

    private static final String agenda = "failure-history";

    private static User user;

    private static List<Failure> failures;

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));

        // test failures
        Indicator indicator1 = new Indicator();
        indicator1.setId(1l);
        indicator1.setDescription("Cann't start");

        Indicator indicator2 = new Indicator();
        indicator2.setId(2l);
        indicator2.setDescription("Doors can't unlock");

        Indicator indicator3 = new Indicator();
        indicator3.setId(3l);
        indicator3.setDescription("Lights off");

        // Failure 1
        Failure failure1 = new Failure();
        failure1.setId(1l);
        failure1.setFailureName("Test failure 1");
        failure1.setDTC("P0000");

        Set<RepairStep> repairSteps1 = new HashSet<>();
        repairSteps1.add(new RepairStep(1l, null, 0, "F1 - Step1"));
        failure1.setRepairSteps(repairSteps1);

        Set<Indicator> indicators1 = new HashSet<>();
        indicators1.add(indicator1);
        indicators1.add(indicator2);
        failure1.setIndicators(indicators1);

        // Failure 2
        Failure failure2 = new Failure();
        failure2.setId(2l);
        failure2.setFailureName("Test failure 2");
        failure2.setDTC("P1000");

        Set<RepairStep> repairSteps2 = new HashSet<>();
        repairSteps2.add(new RepairStep(2l, null, 0, "F2 - Step1"));
        repairSteps2.add(new RepairStep(3l, null, 1, "F2 - Step2"));
        failure2.setRepairSteps(repairSteps2);

        HashSet<Indicator> indicators2 = new HashSet<>();
        indicators2.add(indicator1);
        failure2.setIndicators(indicators2);

        // Failure 3
        Failure failure3 = new Failure();
        failure3.setId(3l);
        failure3.setFailureName("Test failure 3");
        failure3.setDTC("B0000");

        Set<RepairStep> repairSteps3 = new HashSet<>();
        repairSteps3.add(new RepairStep(4l, null, 0, "F3 - Step1"));
        repairSteps3.add(new RepairStep(5l, null, 0, "F3 - Step1"));
        repairSteps3.add(new RepairStep(6l, null, 0, "F3 - Step1"));
        failure3.setRepairSteps(repairSteps3);

        HashSet<Indicator> indicators3 = new HashSet<>();
        indicators3.add(indicator1);
        indicators3.add(indicator2);
        indicators3.add(indicator3);
        failure3.setIndicators(indicators3);

        failures = new ArrayList<>();
        failures.add(failure1);
        failures.add(failure2);
        failures.add(failure3);

        // Vehicle Model
        VehicleModel vehicleModel = new VehicleModel(1L, null, "Punto");

        // DetectedFailures
        DetectedFailure detectedFailure1 = new DetectedFailure();
        detectedFailure1.setFailure(failure2);
        detectedFailure1.setRepairStepApplied(
                failure2.getRepairSteps().stream()
                        .filter(s -> s.getOrderNumber() == 0)
                        .findFirst()
                        .orElse(null));
        detectedFailure1.setTimestamp(LocalDateTime.now());
        detectedFailure1.setVehicleModel(vehicleModel);
        detectedFailure1.setVehicleProductionYear(2011);

        DetectedFailure detectedFailure2 = new DetectedFailure();
        detectedFailure2.setFailure(failure3);
        detectedFailure2.setRepairStepApplied(
                failure3.getRepairSteps().stream()
                        .filter(s -> s.getOrderNumber() == 0)
                        .findFirst()
                        .orElse(null));
        detectedFailure2.setTimestamp(LocalDateTime.now());
        detectedFailure2.setVehicleModel(vehicleModel);
        detectedFailure2.setVehicleProductionYear(2011);

        DetectedFailure detectedFailure3 = new DetectedFailure();
        detectedFailure3.setFailure(failure3);
        detectedFailure3.setRepairStepApplied(
                failure3.getRepairSteps().stream()
                        .filter(s -> s.getOrderNumber() == 1)
                        .findFirst()
                        .orElse(null));
        detectedFailure3.setTimestamp(LocalDateTime.now());
        detectedFailure3.setVehicleModel(vehicleModel);
        detectedFailure3.setVehicleProductionYear(2011);

        DetectedFailure detectedFailure4 = new DetectedFailure();
        detectedFailure4.setFailure(failure3);
        detectedFailure4.setRepairStepApplied(
                failure3.getRepairSteps().stream()
                        .filter(s -> s.getOrderNumber() == 2)
                        .findFirst()
                        .orElse(null));
        detectedFailure4.setTimestamp(LocalDateTime.now());
        detectedFailure4.setVehicleModel(vehicleModel);
        detectedFailure4.setVehicleProductionYear(2011);

        user = new User();
        user.setDetectedFailures(new HashSet<>());
        user.getDetectedFailures().add(detectedFailure1);
        user.getDetectedFailures().add(detectedFailure2);
        user.getDetectedFailures().add(detectedFailure3);
        user.getDetectedFailures().add(detectedFailure4);
    }

    @Test
    public void test_FailureFirstTimeAppearing() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        CurrentDetectedFailure detectedFailure = new CurrentDetectedFailure(
                failures.get(0), failures.get(0).getRepairSteps().size(), 1L, 2011);

        kieSession.insert(user);
        kieSession.insert(detectedFailure);
        kieSession.fireAllRules();

        assertEquals(0, detectedFailure.getNextRepairStep());
    }

    @Test
    public void test_FailureAppearedButNotAllRepairStepsApplied() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        CurrentDetectedFailure detectedFailure = new CurrentDetectedFailure(
                failures.get(1), failures.get(1).getRepairSteps().size(), 1L, 2011);

        kieSession.insert(user);
        kieSession.insert(detectedFailure);
        kieSession.fireAllRules();

        assertEquals(1, detectedFailure.getNextRepairStep());
    }

    @Test
    public void test_FailureAppearedAllRepairStepsApplied() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        CurrentDetectedFailure detectedFailure = new CurrentDetectedFailure(
                failures.get(2), failures.get(2).getRepairSteps().size(), 1L, 2011);

        kieSession.insert(user);
        kieSession.insert(detectedFailure);
        kieSession.fireAllRules();

        assertEquals(0, detectedFailure.getNextRepairStep());
    }
}
