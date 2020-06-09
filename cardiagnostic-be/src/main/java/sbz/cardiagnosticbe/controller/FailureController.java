package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.DtcParamsDTO;
import sbz.cardiagnosticbe.dto.FailureDTO;
import sbz.cardiagnosticbe.model.Failure;
import sbz.cardiagnosticbe.service.FailureService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/failure")
public class FailureController {

    @Autowired
    private FailureService failureService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addFailure(@Valid @RequestBody FailureDTO failureReq) {
        try {
            failureService.addFailure(failureReq);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            value = "/dtc",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Failure>> getFailureFromDtc(@Valid @RequestBody DtcParamsDTO dtcReq) {
        try {
            List<Failure> result = failureService.getFailuresByDtc(dtcReq);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
