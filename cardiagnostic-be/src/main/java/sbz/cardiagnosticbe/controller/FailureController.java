package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.TFailure;
import sbz.cardiagnosticbe.service.FailureService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/failure")
public class FailureController {

    @Autowired
    private FailureService failureService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> addFailure(@Valid @RequestBody TFailure failureReq) {
        try {
            failureService.addFailure(failureReq);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
