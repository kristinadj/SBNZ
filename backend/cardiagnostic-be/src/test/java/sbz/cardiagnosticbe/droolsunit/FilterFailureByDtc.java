package sbz.cardiagnosticbe.droolsunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.model.db.RepairStep;
import sbz.cardiagnosticbe.model.drools.Dtc;
import sbz.cardiagnosticbe.model.drools.PossibleFailuresList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class FilterFailureByDtc {

    private static KieContainer kieContainer;

    private static final String agenda = "filter-by-dtc";

    private static List<Failure> failures;


    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));

        failures = new ArrayList<>();
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

        Indicator indicator4 = new Indicator();
        indicator4.setId(4l);
        indicator4.setDescription("ABC light");

        Indicator indicator5 = new Indicator();
        indicator5.setId(5l);
        indicator5.setDescription("Dark smoke");

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
        repairSteps3.add(new RepairStep(3l, null, 0, "F3 - Step1"));
        failure3.setRepairSteps(repairSteps3);

        HashSet<Indicator> indicators3 = new HashSet<>();
        indicators3.add(indicator3);
        indicators3.add(indicator2);
        failure3.setIndicators(indicators3);

        // Failure 4
        Failure failure4 = new Failure();
        failure4.setId(4l);
        failure4.setFailureName("Test failure 4");
        failure4.setDTC("B1000");

        Set<RepairStep> repairSteps4 = new HashSet<>();
        repairSteps4.add(new RepairStep(4l, null, 0, "F4 - Step1"));
        failure4.setRepairSteps(repairSteps4);

        HashSet<Indicator> indicators4 = new HashSet<>();
        indicators4.add(indicator1);
        indicators4.add(indicator5);
        indicators4.add(indicator3);
        failure4.setIndicators(indicators4);

        // Failure 5
        Failure failure5 = new Failure();
        failure5.setId(5l);
        failure5.setFailureName("Test failure 5");
        failure5.setDTC("U0000");

        Set<RepairStep> repairSteps5 = new HashSet<>();
        repairSteps5.add(new RepairStep(5l, null, 0, "F5 - Step1"));
        failure5.setRepairSteps(repairSteps5);

        HashSet<Indicator> indicators5 = new HashSet<>();
        indicators5.add(indicator1);
        indicators5.add(indicator2);
        failure5.setIndicators(indicators5);

        // Failure 6
        Failure failure6 = new Failure();
        failure6.setId(6l);
        failure6.setFailureName("Test failure 6");
        failure6.setDTC("U1000");

        Set<RepairStep> repairSteps6 = new HashSet<>();
        repairSteps6.add(new RepairStep(6l, null, 0, "F6 - Step1"));
        failure6.setRepairSteps(repairSteps6);

        HashSet<Indicator> indicators6 = new HashSet<>();
        indicators6.add(indicator4);
        indicators6.add(indicator1);
        failure6.setIndicators(indicators6);

        // Failure 7
        Failure failure7 = new Failure();
        failure7.setId(7l);
        failure7.setFailureName("Test failure 7");
        failure7.setDTC("C0000");

        Set<RepairStep> repairSteps7 = new HashSet<>();
        repairSteps7.add(new RepairStep(7l, null, 0, "F7 - Step1"));
        failure7.setRepairSteps(repairSteps7);

        HashSet<Indicator> indicators7 = new HashSet<>();
        indicators7.add(indicator5);
        failure7.setIndicators(indicators7);

        // Failure 8
        Failure failure8 = new Failure();
        failure8.setId(8l);
        failure8.setFailureName("Test failure 8");
        failure8.setDTC("C1000");

        Set<RepairStep> repairSteps8 = new HashSet<>();
        repairSteps8.add(new RepairStep(8l, null, 0, "F8 - Step1"));
        failure8.setRepairSteps(repairSteps8);

        HashSet<Indicator> indicators8 = new HashSet<>();
        indicators8.add(indicator1);
        indicators8.add(indicator2);
        indicators8.add(indicator3);
        failure8.setIndicators(indicators8);

        failures.add(failure1);
        failures.add(failure2);
        failures.add(failure3);
        failures.add(failure4);
        failures.add(failure5);
        failures.add(failure6);
        failures.add(failure7);
        failures.add(failure8);
    }

    @Test
    public void test_filterByP01_foundNone() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("P11");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(0, resultFailures.getFailures().size());
    }

    @Test
    public void test_filterByP00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("P00");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(1l, failure.getId());
        assertEquals("Test failure 1", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("P0000", failure.getDTC());
        assertEquals(2, failure.getIndicators().size());
    }

    @Test
    public void test_filterByP10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("P10");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(2l, failure.getId());
        assertEquals("Test failure 2", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("P1000", failure.getDTC());
        assertEquals(1, failure.getIndicators().size());
    }

    @Test
    public void test_filterByB00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("B00");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(3l, failure.getId());
        assertEquals("Test failure 3", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("B0000", failure.getDTC());
        assertEquals(2, failure.getIndicators().size());
    }

    @Test
    public void test_filterByB10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("B10");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(4l, failure.getId());
        assertEquals("Test failure 4", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("B1000", failure.getDTC());
        assertEquals(3, failure.getIndicators().size());
    }

    @Test
    public void test_filterByC00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("C00");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(7l, failure.getId());
        assertEquals("Test failure 7", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("C0000", failure.getDTC());
        assertEquals(1, failure.getIndicators().size());
    }

    @Test
    public void test_filterByC10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("C10");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(8l, failure.getId());
        assertEquals("Test failure 8", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("C1000", failure.getDTC());
        assertEquals(3, failure.getIndicators().size());
    }

    @Test
    public void test_filterByU00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("U00");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(5l, failure.getId());
        assertEquals("Test failure 5", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("U0000", failure.getDTC());
        assertEquals(2, failure.getIndicators().size());
    }

    @Test
    public void test_filterByU10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());

        Dtc dtc = new Dtc("U10");
        kieSession.insert(dtc);
        kieSession.insert(resultFailures);
        failures.forEach(kieSession::insert);

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0).getFailure();

        assertEquals(6l, failure.getId());
        assertEquals("Test failure 6", failure.getFailureName());
        assertEquals(1, failure.getRepairSteps().size());
        assertEquals("U1000", failure.getDTC());
        assertEquals(2, failure.getIndicators().size());
    }
}
