package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sbz.cardiagnosticbe.dto.vehicle.TVehicleManufacturer;
import sbz.cardiagnosticbe.model.db.VehicleManufacturer;
import sbz.cardiagnosticbe.service.VehicleManufacturerService;
import sbz.cardiagnosticbe.service.VehicleModelService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/vehicleManufacturers")
public class VehicleManufacturerController {

    @Autowired
    private VehicleManufacturerService vehicleManufacturerService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT') || hasAuthority('USER')")
    public ResponseEntity<List<TVehicleManufacturer>> getAll() {
        List<VehicleManufacturer> vehicleManufacturers = vehicleManufacturerService.getAll();
        List<TVehicleManufacturer> result = vehicleManufacturers.stream().map
                (v -> new TVehicleManufacturer(v, vehicleModelService.getByVehicleManufacturerId(v.getId()))).collect(Collectors.toList());
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity add(@RequestBody TVehicleManufacturer tVehicleManufacturer) {
        vehicleManufacturerService.add(tVehicleManufacturer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value =" /{id}",
            method = RequestMethod.PUT
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity update(@PathVariable Long id, @RequestParam String name) {
        vehicleManufacturerService.updateName(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value =" /{id}",
            method = RequestMethod.DELETE
    )
    @PreAuthorize("hasAuthority('EXPERT')")
    public ResponseEntity delete(@PathVariable Long id) {
        vehicleManufacturerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
