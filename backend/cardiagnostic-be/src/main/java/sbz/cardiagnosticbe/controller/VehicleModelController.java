package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.vehicle.TVehicleModel;
import sbz.cardiagnosticbe.service.VehicleModelService;

@RestController
@RequestMapping(value = "/api/vehicleModels")
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity add(@RequestBody TVehicleModel tVehicleModel) {
        vehicleModelService.add(tVehicleModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
