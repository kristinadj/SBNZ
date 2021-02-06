package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbz.cardiagnosticbe.dto.failure.TRelatedFailures;
import sbz.cardiagnosticbe.exception.RelatedFailureException;
import sbz.cardiagnosticbe.repository.DetectedRelatedFailuresRepository;
import sbz.cardiagnosticbe.service.DetectedRelatedFailuresService;
import sbz.cardiagnosticbe.service.RelatedFailuresService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/relatedFailures")
public class RelatedFailuresController {

    @Autowired
    private RelatedFailuresService relatedFailuresService;

    @Autowired
    private DetectedRelatedFailuresService detectedRelatedFailuresService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity addRelatedFailures(@Valid @RequestBody TRelatedFailures req) {
        if (req.getFailuresIds().size() < 2)
            throw new RelatedFailureException("Less than 2 failures selected");

        relatedFailuresService.add(req);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity removeRelatedFailuresEntity(@PathVariable Long id) {
        if (!detectedRelatedFailuresService.findByRelatedFailuresId(id).isEmpty()) {
            relatedFailuresService.remove(id);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
