package sbz.cardiagnosticbe.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.TFailure;
import sbz.cardiagnosticbe.model.Failure;
import sbz.cardiagnosticbe.repository.FailureRepository;

@Service
public class FailureService {

    @Autowired
    private FailureRepository failureRepository;

    private final KieContainer kieContainer;

    @Autowired
    public  FailureService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void addFailure(TFailure failureReq) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(failureReq);
        int fired = kieSession.fireAllRules();

        System.out.println("Addin failure - fired: " + fired);

        kieSession.dispose();

        Failure failure = new Failure();
        failure.setFailureName(failureReq.getFailureName());
        failure.setRepairSolution(failureReq.getRepairSolution());
        failure.setDTC(failureReq.getDtcCode());

        failureRepository.save(failure);
    }
}
