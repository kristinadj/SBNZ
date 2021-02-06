package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.exception.FailureException;
import sbz.cardiagnosticbe.model.db.DetectedRelatedFailures;
import sbz.cardiagnosticbe.model.db.RelatedFailures;
import sbz.cardiagnosticbe.repository.DetectedRelatedFailuresRepository;
import sbz.cardiagnosticbe.repository.RelatedFailuresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetectedRelatedFailuresService {
    @Autowired
    private RelatedFailuresRepository relatedFailuresRepository;

    @Autowired
    private DetectedRelatedFailuresRepository detectedRelatedFailuresRepository;

    public List<DetectedRelatedFailures> findByRelatedFailuresId(Long id) {
        Optional<RelatedFailures> relatedFailures = relatedFailuresRepository.findById(id);
        if (relatedFailures.isEmpty()) {
            throw new FailureException("Invalida failure ID");
        }
        return detectedRelatedFailuresRepository.findAllByRelatedFailure(relatedFailures.get());
    }
}
