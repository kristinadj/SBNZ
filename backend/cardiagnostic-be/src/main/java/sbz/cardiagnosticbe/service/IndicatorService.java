package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.model.Indicator;
import sbz.cardiagnosticbe.repository.IndicatorRepository;

@Service
public class IndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    public Indicator getById(Long id) {
        return indicatorRepository.findById(id).orElse(null);
    }
}
