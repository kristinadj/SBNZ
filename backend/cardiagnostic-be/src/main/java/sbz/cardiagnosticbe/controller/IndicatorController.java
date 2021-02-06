package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.indicator.TIndicator;
import sbz.cardiagnosticbe.model.db.Indicator;
import sbz.cardiagnosticbe.service.IndicatorService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/indicators")
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    @RequestMapping(
            value="/asStrings",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT') || hasAuthority('USER')")
    public ResponseEntity<List<String>> getAllAsStrings() {
        List<Indicator> indicators = indicatorService.getAll();
        List<String> result = indicators.stream().map
                (i -> i.getDescription()).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT') || hasAuthority('USER')")
    public ResponseEntity<List<TIndicator>> getAll() {
        List<Indicator> indicators = indicatorService.getAll();
        List<TIndicator> result = indicators.stream().map
                (i -> new TIndicator(i)).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
