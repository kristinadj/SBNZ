package sbz.cardiagnosticbe.droolsunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import sbz.cardiagnosticbe.model.db.*;
import sbz.cardiagnosticbe.model.drools.DetectedRelatedFailuresProblems;
import sbz.cardiagnosticbe.model.drools.PossibleFailure;
import sbz.cardiagnosticbe.model.drools.PossibleFailuresList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class DetectRelatedFailures {

    private static KieContainer kieContainer;

    private static final String agenda = "related-failures";

    private static List<Failure> failures;
    private static  List<RelatedFailures> relatedFailuresList;

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));

        // Failures
        Failure failure1 = new Failure();
        failure1.setId(1l);
        failure1.setFailureName("Test failure 1");
        failure1.setDTC("B0000");
        failure1.setManufacturerSpecific(false);
        failure1.setIndicators(new HashSet<>());

        Failure failure2 = new Failure();
        failure2.setId(2l);
        failure2.setFailureName("Test failure 2");
        failure2.setDTC("P0000");
        failure2.setManufacturerSpecific(false);
        failure2.setIndicators(new HashSet<>());

        failures = new ArrayList<>();
        failures.add(failure1);
        failures.add(failure2);

        // RelatedFailures
        RelatedFailures relatedFailures = new RelatedFailures();
        relatedFailures.setFailures(new HashSet<>());
        relatedFailures.getFailures().add(failure1);
        relatedFailures.getFailures().add(failure2);
        relatedFailures.setRepairDescription("SOme repair description");

        relatedFailuresList = new ArrayList<>();
        relatedFailuresList.add(relatedFailures);
    }

    @Test
    public void test_DetectNone() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        Failure failure3 = new Failure();
        failure3.setId(3l);
        failure3.setFailureName("Test failure 3");
        failure3.setDTC("B0001");
        failure3.setManufacturerSpecific(false);
        failure3.setIndicators(new HashSet<>());


        DetectedRelatedFailuresProblems detectedRelatedFailuresProblems = new DetectedRelatedFailuresProblems(new ArrayList<>());
        PossibleFailuresList possibleFailuresList = new PossibleFailuresList(new ArrayList<>());
        possibleFailuresList.getFailures().add(new PossibleFailure(failure3, 3));
        possibleFailuresList.getFailures().add(new PossibleFailure(failures.get(1), 2));

        kieSession.insert(detectedRelatedFailuresProblems);
        kieSession.insert(possibleFailuresList);
        relatedFailuresList.forEach(kieSession::insert);
        kieSession.fireAllRules();

        assertEquals(0, detectedRelatedFailuresProblems.getRelatedFailuresProblems().size());

    }

    @Test
    public void test_Detect() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        DetectedRelatedFailuresProblems detectedRelatedFailuresProblems = new DetectedRelatedFailuresProblems(new ArrayList<>());
        PossibleFailuresList possibleFailuresList = new PossibleFailuresList(new ArrayList<>());
        possibleFailuresList.getFailures().add(new PossibleFailure(failures.get(0), 3));
        possibleFailuresList.getFailures().add(new PossibleFailure(failures.get(1), 2));

        kieSession.insert(detectedRelatedFailuresProblems);
        kieSession.insert(possibleFailuresList);
        relatedFailuresList.forEach(kieSession::insert);
        kieSession.fireAllRules();

        assertEquals(1, detectedRelatedFailuresProblems.getRelatedFailuresProblems().size());
    }
}
