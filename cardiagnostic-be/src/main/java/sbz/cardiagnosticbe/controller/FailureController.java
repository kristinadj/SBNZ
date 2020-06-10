package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbz.cardiagnosticbe.dto.DtcParamsDTO;
import sbz.cardiagnosticbe.dto.FailureDTO;
import sbz.cardiagnosticbe.model.Failure;
import sbz.cardiagnosticbe.model.Indicator;
import sbz.cardiagnosticbe.model.enums.CarState;
import sbz.cardiagnosticbe.service.FailureService;
import sbz.cardiagnosticbe.service.IndicatorService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/failure")
public class FailureController {

    @Autowired
    private FailureService failureService;

    @Autowired
    private IndicatorService indicatorService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addFailure(@Valid @RequestBody FailureDTO failureReq) {
        failureService.addFailure(failureReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/detect/{carStateId}",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Failure>> getPossibleFailure(@RequestBody List<Long> indicatorsIds, @PathVariable int carStateId) {
        CarState carState = CarState.fromInteger(carStateId);
        Set<Indicator> indicators = new HashSet<>();

        for (Long id: indicatorsIds) {
            Indicator indicator = indicatorService.getById(id);

            if (indicator != null) {
                indicators.add(indicator);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        List<Failure> result = failureService.getPossibleFailures(indicators, carState);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/byDtc",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Failure>> getFailureFromDtc(@Valid @RequestBody DtcParamsDTO dtcReq) {
        List<Failure> result = failureService.getFailuresByDtc(dtcReq);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
