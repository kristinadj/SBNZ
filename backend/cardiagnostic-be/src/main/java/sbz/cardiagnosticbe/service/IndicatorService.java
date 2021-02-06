package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.repository.IndicatorRepository;

import java.util.List;

@Service
public class IndicatorService {

    @Autowired
    private IndicatorRepository indicatorRepository;

    public Indicator getById(Long id) {
        return indicatorRepository.findById(id).orElse(null);
    }

    public List<Indicator> getAll() {
        return indicatorRepository.findAll();
    }
}
