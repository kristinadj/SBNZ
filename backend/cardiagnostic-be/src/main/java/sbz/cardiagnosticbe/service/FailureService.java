package sbz.cardiagnosticbe.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.detectedFailure.TDetectedFailure;
import sbz.cardiagnosticbe.dto.detectedFailure.TDetectedRelatedFailuresProblem;
import sbz.cardiagnosticbe.dto.detectedFailure.TDetectionResult;
import sbz.cardiagnosticbe.dto.failure.TDtcParams;
import sbz.cardiagnosticbe.dto.failure.TEditFailure;
import sbz.cardiagnosticbe.dto.failure.TNewFailure;
import sbz.cardiagnosticbe.exception.FailureException;
import sbz.cardiagnosticbe.exception.VehicleModelException;
import sbz.cardiagnosticbe.model.db.*;
import sbz.cardiagnosticbe.model.drools.CurrentDetectedFailure;
import sbz.cardiagnosticbe.model.drools.DetectedRelatedFailuresProblems;
import sbz.cardiagnosticbe.model.drools.PossibleFailuresList;
import sbz.cardiagnosticbe.model.drools.DetectFailureParameters;
import sbz.cardiagnosticbe.repository.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FailureService {

    @Autowired
    private FailureRepository failureRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private FailureVehicleInformationRepository failureVehicleInformationRepository;

    @Autowired
    private RelatedFailuresRepository relatedFailuresRepository;

    @Autowired
    private UserRepository userRepository;

    private final KieContainer kieContainer;

    private static final Logger logger = LoggerFactory.getLogger(FailureService.class);

    public FailureService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Failure findById(Long id) {
        Optional<Failure> failure = failureRepository.findById(id);
        if (failure.isEmpty()) {
            throw  new FailureException("Invalid failure ID");
        }

        return failure.get();
    }

    public List<Failure> getAll() {
        return failureRepository.findAll();
    }

    public List<Failure> findByIsManufacturerSpecific(boolean isManufacturerSpecific) {
        return failureRepository.findAllByIsManufacturerSpecific(isManufacturerSpecific);
    }

    public void remove(Long id) {
        failureRepository.deleteById(id);
    }

    public void addFailure(TNewFailure failureReq) {
        if (failureReq.getIndicators().isEmpty()) {
            throw  new FailureException("No indicators selected");
        }

        if (failureReq.getRepairSteps().isEmpty()) {
            throw  new FailureException("Repair steps not added");
        }

        if (failureReq.isManufacturerSpecific()) {
            if (failureReq.getVehicleModelId() == 0 ||
                failureReq.getMaxVehicleProductionYear() == 0 ||
                failureReq.getMinVehicleProductionYear() == 0) {
                throw new VehicleModelException("Invalid vehicle information");
            }

            if (failureReq.getMinVehicleProductionYear() > failureReq.getMaxVehicleProductionYear()) {
                throw new VehicleModelException("Invalid vehicle production years");
            }
        }
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(failureReq);

        kieSession.getAgenda().getAgendaGroup("add-failure").setFocus();
        logger.info("Adding failure - fired: " + kieSession.fireAllRules());

        String failureCnt = String.format("%02d", failureRepository.countByDTCStartingWith(failureReq.getDtcCode()));

        Failure failure = new Failure();
        failure.setFailureName(failureReq.getFailureName());
        failure.setDTC(failureReq.getDtcCode() + failureCnt);
        failure.setManufacturerSpecific(failureReq.isManufacturerSpecific());
        failure.setFailureSeverity(failureReq.getFailureSeverity());

        if (failure.getManufacturerSpecific()) {
            Optional<VehicleModel> vehicleModel = vehicleModelRepository.findById(failureReq.getVehicleModelId());
            if (vehicleModel.isEmpty()) {
                throw new VehicleModelException("Vehicle model not found");
            }

            FailureVehicleInformation failureVehicleInformation = new FailureVehicleInformation();
            failureVehicleInformation.setVehicleModel(vehicleModel.get());
            failureVehicleInformation.setMinVehicleProductionYear(failureReq.getMinVehicleProductionYear());
            failureVehicleInformation.setMaxVehicleProductionYear(failureReq.getMaxVehicleProductionYear());
            failure.setVehicleInformation(failureVehicleInformation);
            failureVehicleInformationRepository.save(failureVehicleInformation);
        }

        for (int i = 0; i < failureReq.getRepairSteps().size(); i++) {
            RepairStep repairStep = new RepairStep();
            repairStep.setOrderNumber(i);
            repairStep.setDescription(failureReq.getRepairSteps().get(i));
            repairStep.setFailure(failure);
            failure.getRepairSteps().add(repairStep);
        }

        HashSet<Indicator> indicators = new HashSet<>();
        for (String indicatorDescription : failureReq.getIndicators()) {
            Indicator indicator = indicatorRepository.findByDescription(indicatorDescription);

            if (indicator == null) {
                indicator = new Indicator();
                indicator.setDescription(indicatorDescription);
                indicatorRepository.save(indicator);
            }
            indicators.add(indicator);
        }

        failure.setIndicators(indicators);
        failureRepository.save(failure);

        kieSession.dispose();
    }


    public List<Failure> getFailuresByDtc(TDtcParams dtcParams) {
        PossibleFailuresList resultFailures = new PossibleFailuresList();
        resultFailures.setFailures(new ArrayList<>());
        List<Failure> allFailures = this.failureRepository.findAll();

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(dtcParams);

        kieSession.getAgenda().getAgendaGroup("format-dtc").setFocus();
        logger.info("Formatting dtc - fired: " + kieSession.fireAllRules());

        kieSession.insert(resultFailures);
        allFailures.forEach(kieSession::insert);

        kieSession.getAgenda().getAgendaGroup("filter-by-dtc").setFocus();
        logger.info("Filtering by dtc - fired: " + kieSession.fireAllRules());

        kieSession.dispose();
        List<Failure> failures = resultFailures.getFailures().stream().map
                (f -> f.getFailure()).collect(Collectors.toList());
        return failures;
    }

    public TDetectionResult detect(Set<Indicator> indicators, VehicleModel vehicleModel, int year, String username) {
        User user = userRepository.findByUsername(username);

        List<Failure> allFailures = failureRepository.findAll();
        List<RelatedFailures> allRelatedFailures = relatedFailuresRepository.findAll();
        PossibleFailuresList resultFailures = new PossibleFailuresList(new ArrayList<>());
        DetectedRelatedFailuresProblems detectedRelatedFailuresProblems = new DetectedRelatedFailuresProblems(new ArrayList<>());

        DetectFailureParameters parameters = new DetectFailureParameters();
        parameters.setIndicators(indicators);
        parameters.setVehicleModelId(vehicleModel.getId());
        parameters.setVehicleProductionYear(year);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(parameters);
        kieSession.insert(resultFailures);
        kieSession.insert(detectedRelatedFailuresProblems);
        allFailures.forEach(kieSession::insert);
        allRelatedFailures.forEach(kieSession::insert);

        kieSession.getAgenda().getAgendaGroup("detect-failures").setFocus();
        logger.info("Detecting possible failures - fired: " + kieSession.fireAllRules());

        kieSession.getAgenda().getAgendaGroup("related-failures").setFocus();
        logger.info("Detecting related failures - fired: " + kieSession.fireAllRules());

        TDetectionResult result = null;
        if (detectedRelatedFailuresProblems.getRelatedFailuresProblems().size() > 0) {
            logger.info("Return related failures problem");

            // add to user history
            DetectedRelatedFailures detected = new DetectedRelatedFailures(null, vehicleModel, year, detectedRelatedFailuresProblems.getRelatedFailuresProblems().get(0), LocalDateTime.now());
            user.getDetectedRelatedFailures().add(detected);
            userRepository.save(user);

            result = new TDetectedRelatedFailuresProblem(
                    detectedRelatedFailuresProblems.getRelatedFailuresProblems().get(0)
            );
        } else {
            kieSession.getAgenda().getAgendaGroup("sort-detected-failures").setFocus();
            PossibleFailuresList sortedFailures = new PossibleFailuresList(new ArrayList<>());
            kieSession.insert(sortedFailures);
            logger.info("Sorting possible failures - fired: " + kieSession.fireAllRules());

            if (sortedFailures.getFailures().size() > 0) {
                CurrentDetectedFailure detectedFailure = new CurrentDetectedFailure(sortedFailures.getFailures().get(0).getFailure(),
                        sortedFailures.getFailures().get(0).getFailure().getRepairSteps().size(), vehicleModel.getId(), year);

                kieSession.insert(user);
                kieSession.insert(detectedFailure);
                kieSession.getAgenda().getAgendaGroup("failure-history").setFocus();
                logger.info("Check failure history - fired: " + kieSession.fireAllRules());

                // add to user history
                RepairStep repairStep = detectedFailure.getFailure().getRepairSteps()
                        .stream()
                        .filter(r -> r.getOrderNumber() == detectedFailure.getNextRepairStep())
                        .findFirst()
                        .orElse(null);
                DetectedFailure detected = new DetectedFailure(null, vehicleModel, year, detectedFailure.getFailure(), LocalDateTime.now(), repairStep);
                user.getDetectedFailures().add(detected);
                userRepository.save(user);

                result = new TDetectedFailure(detectedFailure.getFailure(), repairStep);
            }
        }
        kieSession.dispose();
        return result;
    }


    public void update(Long id, TEditFailure failureReq) {
        Optional<Failure> failureOpt = failureRepository.findById(id);
        if (failureOpt.isEmpty()) {
            throw new FailureException("Invalid failure ID");
        }

        if (failureReq.getIndicators().isEmpty()) {
            throw  new FailureException("No indicators selected");
        }

        if (failureReq.getRepairSteps().isEmpty()) {
            throw  new FailureException("Repair steps not added");
        }

        if (failureReq.isManufacturerSpecific()) {
            if (failureReq.getVehicleModelId() == 0 ||
                    failureReq.getMaxVehicleProductionYear() == 0 ||
                    failureReq.getMinVehicleProductionYear() == 0) {
                throw new VehicleModelException("Invalid vehicle information");
            }

            if (failureReq.getMinVehicleProductionYear() > failureReq.getMaxVehicleProductionYear()) {
                throw new VehicleModelException("Invalid vehicle production years");
            }
        }

        Failure failure = failureOpt.get();
        failure.setFailureName(failureReq.getFailureName());
        failure.setManufacturerSpecific(failureReq.isManufacturerSpecific());
        failure.setFailureSeverity(failureReq.getFailureSeverity());

        if (failure.getManufacturerSpecific()) {
            Optional<VehicleModel> vehicleModel = vehicleModelRepository.findById(failureReq.getVehicleModelId());
            if (vehicleModel.isEmpty()) {
                throw new VehicleModelException("Vehicle model not found");
            }

            FailureVehicleInformation failureVehicleInformation = new FailureVehicleInformation();
            failureVehicleInformation.setVehicleModel(vehicleModel.get());
            failureVehicleInformation.setMinVehicleProductionYear(failureReq.getMinVehicleProductionYear());
            failureVehicleInformation.setMaxVehicleProductionYear(failureReq.getMaxVehicleProductionYear());
            failure.setVehicleInformation(failureVehicleInformation);
            failureVehicleInformationRepository.save(failureVehicleInformation);
        }

        for (int i = 0; i < failureReq.getRepairSteps().size(); i++) {
            RepairStep repairStep = new RepairStep();
            repairStep.setOrderNumber(i);
            repairStep.setDescription(failureReq.getRepairSteps().get(i));
            repairStep.setFailure(failure);
            failure.getRepairSteps().add(repairStep);
        }

        HashSet<Indicator> indicators = new HashSet<>();
        for (String indicatorDescription : failureReq.getIndicators()) {
            Indicator indicator = indicatorRepository.findByDescription(indicatorDescription);

            if (indicator == null) {
                indicator = new Indicator();
                indicator.setDescription(indicatorDescription);
                indicatorRepository.save(indicator);
            }
            indicators.add(indicator);
        }

        failureRepository.save(failure);
    }
}
