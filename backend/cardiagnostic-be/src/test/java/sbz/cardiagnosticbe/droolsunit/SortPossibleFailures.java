package sbz.cardiagnosticbe.droolsunit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.test.context.junit4.SpringRunner;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.drools.PossibleFailure;
import sbz.cardiagnosticbe.model.drools.PossibleFailuresList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class SortPossibleFailures {

    private static KieContainer kieContainer;

    private static final String agenda = "sort-detected-failures";

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void test_Sort() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        PossibleFailuresList possibleFailuresList = new PossibleFailuresList(new ArrayList<>());

        PossibleFailure pf1 = new PossibleFailure();
        pf1.setFailure(new Failure(1l));
        pf1.setMatchingIndicatorsNumber(4);

        PossibleFailure pf2 = new PossibleFailure();
        pf2.setFailure(new Failure(2l));
        pf2.setMatchingIndicatorsNumber(8);

        PossibleFailure pf3 = new PossibleFailure();
        pf3.setFailure(new Failure(3l));
        pf3.setMatchingIndicatorsNumber(1);

        PossibleFailure pf4 = new PossibleFailure();
        pf4.setFailure(new Failure(4l));
        pf4.setMatchingIndicatorsNumber(7);

        PossibleFailure pf5 = new PossibleFailure();
        pf5.setFailure(new Failure(5l));
        pf5.setMatchingIndicatorsNumber(12);

        kieSession.insert(possibleFailuresList);
        kieSession.insert(pf1);
        kieSession.insert(pf2);
        kieSession.insert(pf3);
        kieSession.insert(pf4);
        kieSession.insert(pf5);

        kieSession.fireAllRules();

        assertEquals(5, possibleFailuresList.getFailures().size());
        assertEquals(5l, possibleFailuresList.getFailures().get(0).getFailure().getId());
        assertEquals(2l, possibleFailuresList.getFailures().get(1).getFailure().getId());
        assertEquals(4l, possibleFailuresList.getFailures().get(2).getFailure().getId());
        assertEquals(1l, possibleFailuresList.getFailures().get(3).getFailure().getId());
        assertEquals(3l, possibleFailuresList.getFailures().get(4).getFailure().getId());
    }
}
