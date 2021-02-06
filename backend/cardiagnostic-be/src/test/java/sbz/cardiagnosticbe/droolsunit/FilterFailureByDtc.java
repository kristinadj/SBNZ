package sbz.cardiagnosticbe.droolsunit;

import org.kie.api.runtime.KieContainer;
import sbz.cardiagnosticbe.model.db.Failure;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterFailureByDtc {

    private static KieContainer kieContainer;

    private static final String agenda = "failure-by-dtc";

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

        Failure failure2 = new Failure();
        failure2.setId(2l);
        failure2.setFailureName("Test failure 2");
        failure2.setRepairSolution("Test repair 2");
        failure2.setDTC("P1000");
        failure2.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators2 = new HashSet<>();
        indicators2.add(indicator1);
        failure2.setIndicators(indicators2);

        Failure failure3 = new Failure();
        failure3.setId(3l);
        failure3.setFailureName("Test failure 3");
        failure3.setRepairSolution("Test repair 3");
        failure3.setDTC("B0000");
        failure3.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators3 = new HashSet<>();
        indicators3.add(indicator3);
        indicators3.add(indicator2);
        failure3.setIndicators(indicators3);

        Failure failure4 = new Failure();
        failure4.setId(4l);
        failure4.setFailureName("Test failure 4");
        failure4.setRepairSolution("Test repair 4");
        failure4.setDTC("B1000");
        failure4.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators4 = new HashSet<>();
        indicators4.add(indicator1);
        indicators4.add(indicator5);
        indicators4.add(indicator3);
        failure4.setIndicators(indicators4);

        Failure failure5 = new Failure();
        failure5.setId(5l);
        failure5.setFailureName("Test failure 5");
        failure5.setRepairSolution("Test repair 5");
        failure5.setDTC("U0000");
        failure5.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators5 = new HashSet<>();
        indicators5.add(indicator1);
        indicators5.add(indicator2);
        failure5.setIndicators(indicators5);

        Failure failure6 = new Failure();
        failure6.setId(6l);
        failure6.setFailureName("Test failure 6");
        failure6.setRepairSolution("Test repair 6");
        failure6.setDTC("U1000");
        failure6.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators6 = new HashSet<>();
        indicators6.add(indicator4);
        indicators6.add(indicator1);
        failure6.setIndicators(indicators6);

        Failure failure7 = new Failure();
        failure7.setId(7l);
        failure7.setFailureName("Test failure 7");
        failure7.setRepairSolution("Test repair 7");
        failure7.setDTC("C0000");
        failure7.setCarState(CarState.MOVEMENT);
        HashSet<Indicator> indicators7 = new HashSet<>();
        indicators7.add(indicator5);
        failure7.setIndicators(indicators7);

        Failure failure8 = new Failure();
        failure8.setId(8l);
        failure8.setFailureName("Test failure 8");
        failure8.setRepairSolution("Test repair 8");
        failure8.setDTC("C1000");
        failure8.setCarState(CarState.MOVEMENT);
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

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.POWERTRAIN);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(1);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(0, resultFailures.getFailures().size());
    }

    @Test
    public void test_filterByP00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.POWERTRAIN);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(1l, failure.getId());
        assertEquals("Test failure 1", failure.getFailureName());
        assertEquals("Test repair 1", failure.getRepairSolution());
        assertEquals("P0000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(2, failure.getIndicators().size());
    }

    @Test
    public void test_filterByP10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.POWERTRAIN);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(2l, failure.getId());
        assertEquals("Test failure 2", failure.getFailureName());
        assertEquals("Test repair 2", failure.getRepairSolution());
        assertEquals("P1000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(1, failure.getIndicators().size());
    }

    @Test
    public void test_filterByB00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.BODY);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(3l, failure.getId());
        assertEquals("Test failure 3", failure.getFailureName());
        assertEquals("Test repair 3", failure.getRepairSolution());
        assertEquals("B0000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(2, failure.getIndicators().size());
    }

    @Test
    public void test_filterByB10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.BODY);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(4l, failure.getId());
        assertEquals("Test failure 4", failure.getFailureName());
        assertEquals("Test repair 4", failure.getRepairSolution());
        assertEquals("B1000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(3, failure.getIndicators().size());
    }

    @Test
    public void test_filterByC00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.CHASIS);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(7l, failure.getId());
        assertEquals("Test failure 7", failure.getFailureName());
        assertEquals("Test repair 7", failure.getRepairSolution());
        assertEquals("C0000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(1, failure.getIndicators().size());
    }

    @Test
    public void test_filterByC10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.CHASIS);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(8l, failure.getId());
        assertEquals("Test failure 8", failure.getFailureName());
        assertEquals("Test repair 8", failure.getRepairSolution());
        assertEquals("C1000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(3, failure.getIndicators().size());
    }

    @Test
    public void test_filterByU00_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.NETWORK_VEHICLE_INTEGRATION);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(5l, failure.getId());
        assertEquals("Test failure 5", failure.getFailureName());
        assertEquals("Test repair 5", failure.getRepairSolution());
        assertEquals("U0000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(2, failure.getIndicators().size());
    }

    @Test
    public void test_filterByU10_found1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());

        TDtcParams dto = new TDtcParams();
        dto.setVehiclePart(VehiclePart.NETWORK_VEHICLE_INTEGRATION);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);

        kieSession.insert(dto);
        kieSession.insert(resultFailures);
        for (Failure f: failures) {
            kieSession.insert(f);
        }

        kieSession.fireAllRules();

        assertEquals(1, resultFailures.getFailures().size());

        Failure failure = resultFailures.getFailures().get(0);

        assertEquals(6l, failure.getId());
        assertEquals("Test failure 6", failure.getFailureName());
        assertEquals("Test repair 6", failure.getRepairSolution());
        assertEquals("U1000", failure.getDTC());
        assertEquals(CarState.MOVEMENT, failure.getCarState());
        assertEquals(2, failure.getIndicators().size());
    }
    */
}
