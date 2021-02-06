package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(
            value =" /{id}",
            method = RequestMethod.PUT
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity update(@PathVariable Long id, @RequestParam String name) {
        vehicleModelService.updateName(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value =" /{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity delete(@PathVariable Long id) {
        vehicleModelService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
