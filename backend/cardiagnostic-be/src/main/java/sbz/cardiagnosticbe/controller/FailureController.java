package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbz.cardiagnosticbe.dto.detectedFailure.TDetectionResult;
import sbz.cardiagnosticbe.dto.failure.*;
import sbz.cardiagnosticbe.model.db.DetectedFailure;
import sbz.cardiagnosticbe.model.db.Failure;
import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.model.db.VehicleModel;
import sbz.cardiagnosticbe.service.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/failures")
public class FailureController {

    @Autowired
    private FailureService failureService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private DetectedFailureService detectedFailureService;

    @Autowired
    private RelatedFailuresService relatedFailuresService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity addFailure(@Valid @RequestBody TNewFailure failureReq) {
        failureService.addFailure(failureReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT') || hasAuthority('USER')")
    public ResponseEntity<List<TFailure>> getAll() {
        List<Failure> failures = failureService.getAll();
        List<TFailure> tFailures = failures.stream().map
                (f -> new TFailure(f)).collect(Collectors.toList());
        return new ResponseEntity<>(tFailures, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/byIsManufacturerSpecific",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity<List<TFailureBasicInfo>> getByIsManufacturerSpecific(@RequestParam boolean isManufacturerSpecific) {
        List<Failure> failures = failureService.findByIsManufacturerSpecific(isManufacturerSpecific);
        List<TFailureBasicInfo> tFailures = failures.stream().map
                (f -> new TFailureBasicInfo(f)).collect(Collectors.toList());
        return new ResponseEntity<>(tFailures, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/detect",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<TDetectionResult> detect(Principal principal, @RequestBody TDetectFailure req) {
        String username = principal.getName();
        Set<Indicator> indicators = new HashSet<>();
        for (Long id: req.getIndicatorsIds()) {
            Indicator indicator = indicatorService.getById(id);
            indicators.add(indicator);
        }
        VehicleModel vehicleModel = vehicleModelService.getById(req.getVehicleModelId());

        TDetectionResult result = failureService.detect(indicators, vehicleModel, req.getVehicleProductionYear(), username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/byDtc",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT') || hasAuthority('USER')")
    public ResponseEntity<List<TFailure>> getFailureFromDtc(@Valid @RequestBody TDtcParams req) {
        List<Failure> failures = failureService.getFailuresByDtc(req);
        List<TFailure> tFailures = failures.stream().map
                (f -> new TFailure(f)).collect(Collectors.toList());
        return new ResponseEntity<>(tFailures, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody TEditFailure req) {
        failureService.update(id, req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('EXPERT')")

    public ResponseEntity update(@PathVariable Long id) {
        Failure failure = failureService.findById(id);
        if (!detectedFailureService.findByFailureId(failure).isEmpty() &&
            !relatedFailuresService.findByFailureId(failure).isEmpty()) {
            failureService.remove(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

