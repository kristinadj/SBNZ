package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.model.Symptom;
import sbz.cardiagnosticbe.repository.SymptomRepository;

@Service
public class SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    public Symptom getById(Long id) {
        return symptomRepository.getOne(id);
    }
}
