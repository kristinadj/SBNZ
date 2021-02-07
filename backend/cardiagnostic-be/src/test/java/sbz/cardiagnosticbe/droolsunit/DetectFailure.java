package sbz.cardiagnosticbe.droolsunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import sbz.cardiagnosticbe.model.db.*;
import sbz.cardiagnosticbe.model.drools.DetectFailureParameters;
import sbz.cardiagnosticbe.model.drools.PossibleFailuresList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class DetectFailure {

    private static KieContainer kieContainer;

    private static final String agenda = "detect-failure";

    private static List<Failure> failures;

    @Before
    public void createContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));

        // Indicators
        Indicator indicator1 = new Indicator(1l, "Cann't start");
        Indicator indicator2 = new Indicator(2l, "Doors can't unlock");

        // Failures
        Failure failure1 = new Failure();
        failure1.setId(1l);
        failure1.setFailureName("Test failure 1");
        failure1.setDTC("B0000");
        failure1.setManufacturerSpecific(false);

        Set<Indicator> indicators1 = new HashSet<>();
        indicators1.add(indicator1);
        indicators1.add(indicator2);
        failure1.setIndicators(indicators1);

        Failure failure2 = new Failure();
        failure2.setId(2l);
        failure2.setFailureName("Test failure 2");
        failure2.setDTC("P0000");
        failure2.setManufacturerSpecific(true);

        VehicleModel vehicleModel = new VehicleModel(1l, null, "Punto");
        failure2.setVehicleInformation(new FailureVehicleInformation(1l, vehicleModel, 2007, 2012));

        Set<Indicator> indicators2 = new HashSet<>();
        indicators2.add(indicator1);
        indicators2.add(indicator2);
        failure2.setIndicators(indicators2);

        failures = new ArrayList<>();
        failures.add(failure1);
        failures.add(failure2);
    }

    @Test
    public void test_detectNone() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList(new ArrayList<>());

        Set<Indicator> indicators = new HashSet<>();
        Indicator indicator = new Indicator(4l, "ABC light");
        indicators.add(indicator);

        DetectFailureParameters detectFailureParameters = new DetectFailureParameters();
        detectFailureParameters.setVehicleProductionYear(2011);
        detectFailureParameters.setVehicleModelId(1l);
        detectFailureParameters.setIndicators(indicators);

        kieSession.insert(detectFailureParameters);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(0, resultFailures.getFailures().size());
    }

    @Test
    public void test_detectGenericAndManufacturerSpecific() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList(new ArrayList<>());

        Set<Indicator> indicators = new HashSet<>();
        Indicator indicator1 = new Indicator(1l, "Cann't start");
        Indicator indicator2 = new Indicator(2l, "Doors can't unlock");
        indicators.add(indicator1);
        indicators.add(indicator2);

        DetectFailureParameters detectFailureParameters = new DetectFailureParameters();
        detectFailureParameters.setVehicleProductionYear(2011);
        detectFailureParameters.setVehicleModelId(1l);
        detectFailureParameters.setIndicators(indicators);

        kieSession.insert(detectFailureParameters);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(2, resultFailures.getFailures().size());
    }

    @Test
    public void test_detectOnlyGeneric() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList(new ArrayList<>());

        Set<Indicator> indicators = new HashSet<>();
        Indicator indicator1 = new Indicator(1l, "Cann't start");
        Indicator indicator2 = new Indicator(2l, "Doors can't unlock");
        indicators.add(indicator1);
        indicators.add(indicator2);

        DetectFailureParameters detectFailureParameters = new DetectFailureParameters();
        detectFailureParameters.setVehicleProductionYear(2015);
        detectFailureParameters.setVehicleModelId(2l);
        detectFailureParameters.setIndicators(indicators);

        kieSession.insert(detectFailureParameters);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());
    }
}
