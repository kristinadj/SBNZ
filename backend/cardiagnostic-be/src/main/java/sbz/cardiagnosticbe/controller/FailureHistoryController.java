package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.failure.THistoryFailure;
import sbz.cardiagnosticbe.model.db.User;
import sbz.cardiagnosticbe.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/failureHistory")
public class FailureHistoryController {
    @Autowired
    private UserService userService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<THistoryFailure>> get(Principal principal, @RequestParam boolean sortRecently) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<THistoryFailure> detectedFailures = user.getDetectedFailures().stream().map
                (f -> new THistoryFailure(f)).collect(Collectors.toList());
        List<THistoryFailure> detectedRelatedFailures = user.getDetectedRelatedFailures().stream().map
                (rf -> new THistoryFailure(rf)).collect(Collectors.toList());
        detectedFailures.addAll(detectedRelatedFailures);

        Collections.sort(detectedFailures);
        if (sortRecently) {
            Collections.reverse(detectedFailures);
        }

        return new ResponseEntity<>(detectedFailures, HttpStatus.OK);
    }

}
