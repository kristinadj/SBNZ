package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.vehicle.TVehicleManufacturer;
import sbz.cardiagnosticbe.exception.VehicleManufacturerException;
import sbz.cardiagnosticbe.model.db.VehicleManufacturer;
import sbz.cardiagnosticbe.repository.VehicleManufacturerRepository;

import java.util.List;

@Service
public class VehicleManufacturerService {

    @Autowired
    private VehicleManufacturerRepository vehicleManufacturerRepository;

    public List<VehicleManufacturer> getAll() {
        return vehicleManufacturerRepository.findAll();
    }

    public void add(TVehicleManufacturer tVehicleManufacturer) {
        if (vehicleManufacturerRepository.findByName(tVehicleManufacturer.getName()) != null) {
            throw new VehicleManufacturerException("Manufacturer with that name already exist");
        }

        VehicleManufacturer vehicleManufacturer = new VehicleManufacturer();
        vehicleManufacturer.setName(tVehicleManufacturer.getName());
        vehicleManufacturerRepository.save(vehicleManufacturer);
    }
}
