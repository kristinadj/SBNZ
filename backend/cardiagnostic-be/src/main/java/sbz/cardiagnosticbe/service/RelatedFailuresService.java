package sbz.cardiagnosticbe.service;

import org.apache.tools.ant.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.failure.TRelatedFailures;
import sbz.cardiagnosticbe.exception.FailureException;
import sbz.cardiagnosticbe.exception.NotDisjunctIndicatorsException;
import sbz.cardiagnosticbe.exception.RelatedFailureException;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.model.db.RelatedFailures;
import sbz.cardiagnosticbe.repository.FailureRepository;
import sbz.cardiagnosticbe.repository.RelatedFailuresRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RelatedFailuresService {

    @Autowired
    private RelatedFailuresRepository relatedFailuresRepository;

    @Autowired
    private FailureRepository failureRepository;

    public void add(TRelatedFailures req) {
        // check if this combinations of failures is already added
        List<RelatedFailures> allRelatedFailures = relatedFailuresRepository.findAll();
        for (RelatedFailures rf : allRelatedFailures) {
            List<Long> failureIds = rf.getFailures().stream().map(f -> f.getId()).collect(Collectors.toList());
            if (failureIds.size() == req.getFailuresIds().size() &&  failureIds.containsAll(req.getFailuresIds())){
                throw new RelatedFailureException("Combination of failures already added");
            }
        }

        RelatedFailures relatedFailures = new RelatedFailures();
        relatedFailures.setRepairDescription(req.getRepairDescription());

        List<Indicator> indicatorList = new ArrayList<>();
        for (Long id : req.getFailuresIds()) {
            Optional<Failure> failure = failureRepository.findById(id);

            if (!failure.isPresent()) {
                throw new FailureException("Invalid ID");
            }

            // check if indicators are disjunct
            Set<Indicator> intersection = failure.get().getIndicators().stream()
                    .filter(indicatorList::contains)
                    .collect(Collectors.toSet());
            if (!intersection.isEmpty()) {
                throw new NotDisjunctIndicatorsException();
            }

            indicatorList.addAll(failure.get().getIndicators());
            relatedFailures.getFailures().add(failure.get());
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

    public List<RelatedFailures> findByFailureId(Failure failure) {
        return relatedFailuresRepository.findAllByFailuresContains(failure);
    }
}
