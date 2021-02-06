package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.vehicle.TVehicleManufacturer;
import sbz.cardiagnosticbe.exception.VehicleManufacturerException;
import sbz.cardiagnosticbe.exception.VehicleModelException;
import sbz.cardiagnosticbe.model.db.VehicleManufacturer;
import sbz.cardiagnosticbe.repository.VehicleManufacturerRepository;

import java.util.List;
import java.util.Optional;

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

    public void updateName(Long id, String name) {
        Optional<VehicleManufacturer> vehicleManufacturerOpt = vehicleManufacturerRepository.findById(id);
        if (vehicleManufacturerOpt.isEmpty()) {
            throw new VehicleModelException("Invalid vehicle manufacturer ID");
        }

        if (vehicleManufacturerRepository.findByName(name) != null) {
            throw new VehicleManufacturerException("Manufacturer with that name already exist");
        }

        VehicleManufacturer vehicleManufacturer = vehicleManufacturerOpt.get();
        vehicleManufacturer.setName(name);
        vehicleManufacturerRepository.save(vehicleManufacturer);
    }

    public void remove(Long id) {
        Optional<VehicleManufacturer> vehicleManufacturerOpt = vehicleManufacturerRepository.findById(id);
        if (vehicleManufacturerOpt.isEmpty()) {
            throw new VehicleManufacturerException("Invalid vehicle manufacturer ID");
        }

        VehicleManufacturer vehicleManufacturer = vehicleManufacturerOpt.get();
        if (!vehicleManufacturer.getVehicleModels().isEmpty()) {
            throw  new VehicleManufacturerException("Vehicle models exist - can't remove manufacturer");
        }

        vehicleManufacturerRepository.deleteById(id);
    }
}
