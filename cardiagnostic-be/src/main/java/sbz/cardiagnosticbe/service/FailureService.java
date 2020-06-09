package sbz.cardiagnosticbe.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.DtcParamsDTO;
import sbz.cardiagnosticbe.dto.FailureDTO;
import sbz.cardiagnosticbe.model.drools.FailureList;
import sbz.cardiagnosticbe.model.Failure;
import sbz.cardiagnosticbe.model.Symptom;
import sbz.cardiagnosticbe.model.enums.CarState;
import sbz.cardiagnosticbe.repository.FailureRepository;
import sbz.cardiagnosticbe.repository.SymptomRepository;

import java.util.*;

@Service
public class FailureService {

    @Autowired
    private FailureRepository failureRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    private final KieContainer kieContainer;

    private static final Logger logger = LoggerFactory.getLogger(FailureService.class);

    public FailureService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void addFailure(FailureDTO failureReq) {
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

        HashSet<Symptom> symptoms = new HashSet<>();
        for (String symptomName: failureReq.getSymptoms()) {
            Symptom symptom = symptomRepository.findBySymptomName(symptomName);

            if (symptom == null) {
                symptom = new Symptom();
                symptom.setSymptomName(symptomName);

                symptomRepository.save(symptom);
            }
            symptoms.add(symptom);
        }

        failure.setSymptoms(symptoms);
        failureRepository.save(failure);

        kieSession.dispose();
    }

    public List<Failure> getFailuresByDtc(DtcParamsDTO dtcParams) {
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
}
