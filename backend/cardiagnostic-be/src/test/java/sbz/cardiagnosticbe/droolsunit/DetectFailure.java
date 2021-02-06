package sbz.cardiagnosticbe.droolsunit;

import org.kie.api.runtime.KieContainer;
import sbz.cardiagnosticbe.model.db.Failure;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetectFailure {

    private static KieContainer kieContainer;

    private static final String agenda = "detect-failure";

    private static List<Failure> failures;

    /*
    @BeforeEach
    public void createContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));

        failures = new ArrayList<>();
        // test failures
        Indicator indicator1 = new Indicator();
        indicator1.setId(1l);
        indicator1.setIndicatorName("Cann't start");

        Indicator indicator2 = new Indicator();
        indicator2.setId(2l);
        indicator2.setIndicatorName("Doors can't unlock");

        Indicator indicator3 = new Indicator();
        indicator3.setId(3l);
        indicator3.setIndicatorName("Lights off");

        Indicator indicator4 = new Indicator();
        indicator4.setId(4l);
        indicator4.setIndicatorName("ABC light");

        Indicator indicator5 = new Indicator();
        indicator5.setId(5l);
        indicator5.setIndicatorName("Dark smoke");


        Failure failure1 = new Failure();
        failure1.setId(1l);
        failure1.setFailureName("Test failure 1");
        failure1.setRepairSolution("Test repair 1");
        failure1.setDTC("P0000");
        failure1.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators1 = new HashSet<>();
        indicators1.add(indicator1);
        indicators1.add(indicator2);
        failure1.setIndicators(indicators1);

        failures.add(failure1);
    }

    @Test
    public void test_detectNone() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        Set<Indicator> indicators = new HashSet<>();
        Indicator indicator4 = new Indicator();
        indicator4.setId(4l);
        indicator4.setIndicatorName("ABC light");
        indicators.add(indicator4);

        VisibleIndicators visibleIndicators = new VisibleIndicators();
        visibleIndicators.setIndicators(indicators);
        visibleIndicators.setCarState(CarState.START_UP);

        kieSession.insert(visibleIndicators);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(0, resultFailures.getFailures().size());
    }

    @Test
    public void test_detect1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        Set<Indicator> indicators = new HashSet<>();
        Indicator indicator1 = new Indicator();
        indicator1.setId(1l);
        indicator1.setIndicatorName("Cann't start");
        Indicator indicator2 = new Indicator();
        indicator2.setId(2l);
        indicator2.setIndicatorName("Doors can't unlock");
        indicators.add(indicator1);
        indicators.add(indicator2);

        VisibleIndicators visibleIndicators = new VisibleIndicators();
        visibleIndicators.setIndicators(indicators);
        visibleIndicators.setCarState(CarState.MOVEMENT);

        kieSession.insert(visibleIndicators);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());
    }*/
}
