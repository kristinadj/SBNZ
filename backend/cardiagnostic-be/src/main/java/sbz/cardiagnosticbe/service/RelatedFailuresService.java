package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.failure.TRelatedFailures;
import sbz.cardiagnosticbe.exception.FailureException;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.db.RelatedFailures;
import sbz.cardiagnosticbe.repository.FailureRepository;
import sbz.cardiagnosticbe.repository.RelatedFailuresRepository;

import java.util.Optional;

@Service
public class RelatedFailuresService {

    @Autowired
    private RelatedFailuresRepository relatedFailuresRepository;

    @Autowired
    private FailureRepository failureRepository;

    public void add(TRelatedFailures req) {
        RelatedFailures relatedFailures = new RelatedFailures();
        relatedFailures.setRepairDescription(req.getRepairDescription());
        for (Long id : req.getFailuresIds()) {
            Optional<Failure> failure = failureRepository.findById(id);
            if (!failure.isPresent()) {
                throw new FailureException("Invalid ID");
            } else {
                relatedFailures.getFailures().add(failure.get());
            }
        }
        relatedFailuresRepository.save(relatedFailures);
    }

    public void remove(Long id) {
        Optional<RelatedFailures> relatedFailures = relatedFailuresRepository.findById(id);
        if (relatedFailures.isEmpty()) {
            throw new FailureException("Invalid ID");
        }

        relatedFailuresRepository.deleteById(id);
    }
}
