package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.model.db.DetectedFailure;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.repository.DetectedFailureRepository;
import sbz.cardiagnosticbe.repository.FailureRepository;

import java.util.List;

@Service
public class DetectedFailureService {

    @Autowired
    private DetectedFailureRepository detectedFailureRepository;

    public List<DetectedFailure> findByFailureId(Failure failure) {
        return detectedFailureRepository.findAllByFailure(failure);
    }
}
