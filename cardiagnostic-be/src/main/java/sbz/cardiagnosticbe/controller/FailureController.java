package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbz.cardiagnosticbe.dto.DtcParamsDTO;
import sbz.cardiagnosticbe.dto.FailureDTO;
import sbz.cardiagnosticbe.model.Failure;
import sbz.cardiagnosticbe.model.Symptom;
import sbz.cardiagnosticbe.model.enums.CarState;
import sbz.cardiagnosticbe.service.FailureService;
import sbz.cardiagnosticbe.service.SymptomService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/failure")
public class FailureController {

    @Autowired
    private FailureService failureService;

    @Autowired
    private SymptomService symptomService;

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
    public ResponseEntity<List<Failure>> getPossibleFailure(@RequestBody List<Long> symptomsIds, @PathVariable int carStateId) {
        CarState carState = CarState.fromInteger(carStateId);
        List<Symptom> symptoms = new ArrayList<>();

        for (Long id: symptomsIds) {
            symptoms.add(symptomService.getById(id));
        }

        List<Failure> result = failureService.getPossibleFailures(symptoms, carState);
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
