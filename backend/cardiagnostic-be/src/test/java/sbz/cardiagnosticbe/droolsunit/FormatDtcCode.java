package sbz.cardiagnosticbe.droolsunit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import sbz.cardiagnosticbe.dto.failure.TFailure;
import sbz.cardiagnosticbe.model.enums.CarState;
import sbz.cardiagnosticbe.model.enums.VehiclePart;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatDtcCode {

    private static KieContainer kieContainer;

    private static final String agenda = "add-failure";

    @BeforeAll
    static void createContainer() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
    }

    @Test
    public void test_DtcCodesP0() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.POWERTRAIN);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("P00", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesP1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.POWERTRAIN);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("P10", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesB0() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.BODY);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("B00", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesB1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.BODY);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("B10", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesC0() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.CHASIS);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("C00", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesC1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.CHASIS);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("C10", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesU0() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.NETWORK_VEHICLE_INTEGRATION);
        dto.setManufacturerSpecific(false);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("U00", dto.getDtcCode());
    }

    @Test
    public void test_DtcCodesU1() {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.getAgenda().getAgendaGroup(agenda).setFocus();

        HashSet<String> indicators = new HashSet<>();
        indicators.add("Test indicator 1");
        indicators.add("Test indicator 2");

        TFailure dto = new TFailure();
        dto.setVehiclePart(VehiclePart.NETWORK_VEHICLE_INTEGRATION);
        dto.setManufacturerSpecific(true);
        dto.setVehicleSubsystem(0);
        dto.setIndicators(indicators);
        dto.setCarState(CarState.MOVEMENT.ordinal());
        dto.setFailureName("Test failure");
        dto.setRepairSolution("Repair solution");

        kieSession.insert(dto);
        kieSession.fireAllRules();

        assertEquals("U10", dto.getDtcCode());
    }
}
