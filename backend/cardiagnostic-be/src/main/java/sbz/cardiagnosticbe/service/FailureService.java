package sbz.cardiagnosticbe.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.TDtcParams;
import sbz.cardiagnosticbe.dto.failure.TFailure;
import sbz.cardiagnosticbe.model.Indicator;
import sbz.cardiagnosticbe.model.drools.FailureList;
import sbz.cardiagnosticbe.model.Failure;
import sbz.cardiagnosticbe.model.drools.VisibleIndicators;
import sbz.cardiagnosticbe.model.enums.CarState;
import sbz.cardiagnosticbe.repository.FailureRepository;
import sbz.cardiagnosticbe.repository.IndicatorRepository;

import java.util.*;

@Service
public class FailureService {

    @Autowired
    private FailureRepository failureRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    private final KieContainer kieContainer;

    private static final Logger logger = LoggerFactory.getLogger(FailureService.class);

    public FailureService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void addFailure(TFailure failureReq) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(failureReq);

        kieSession.getAgenda().getAgendaGroup("add-failure").setFocus();
        logger.info("Adding failure - fired: " + kieSession.fireAllRules());

        String failureCnt = String.format("%03d", failureRepository.countByDTCStartingWith(failureReq.getDtcCode()));

        Failure failure = new Failure();
        failure.setCarState(CarState.fromInteger(failureReq.getCarState()));
        failure.setFailureName(failureReq.getFailureName());
        failure.setRepairSolution(failureReq.getRepairSolution());
        failure.setDTC(failureReq.getDtcCode() + failureCnt);

        HashSet<Indicator> indicators = new HashSet<>();
        for (String indicatorName: failureReq.getIndicators()) {
            Indicator indicator = indicatorRepository.findByIndicatorName(indicatorName);

            if (indicator == null) {
                indicator = new Indicator();
                indicator.setIndicatorName(indicatorName);

                indicatorRepository.save(indicator);
            }
            indicators.add(indicator);
        }

        failure.setIndicators(indicators);
        failureRepository.save(failure);

        kieSession.dispose();
    }

    public List<Failure> getFailuresByDtc(TDtcParams dtcParams) {
        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());
        List<Failure> allFailures = this.failureRepository.findAll();

        KieSession kieSession = kieContainer.newKieSession();

        kieSession.insert(dtcParams);
        kieSession.insert(resultFailures);

        for (Failure f: allFailures) {
            kieSession.insert(f);
        }

        kieSession.getAgenda().getAgendaGroup("failure-by-dtc").setFocus();
        logger.info("Filtering failures by dtc - fired: " + kieSession.fireAllRules());

        kieSession.dispose();
        return resultFailures.getFailures();
    }

    public List<Failure> getPossibleFailures(Set<Indicator> indicators, CarState carState) {
        FailureList resultFailures = new FailureList();
        resultFailures.setFailures(new ArrayList<>());
        VisibleIndicators visibleIndicators = new VisibleIndicators();
        visibleIndicators.setIndicators(indicators);
        visibleIndicators.setCarState(carState);
        List<Failure> allFailures = this.failureRepository.findAll();

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(resultFailures);
        kieSession.insert(visibleIndicators);

        for (Failure f: allFailures) {
            kieSession.insert(f);
        }

        kieSession.getAgenda().getAgendaGroup("detect-failure").setFocus();
        logger.info("Detecting failures by - fired: " + kieSession.fireAllRules());

        kieSession.dispose();
        return resultFailures.getFailures();
    }
}
