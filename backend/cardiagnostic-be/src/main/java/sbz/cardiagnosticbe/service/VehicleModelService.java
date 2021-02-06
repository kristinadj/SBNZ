package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.dto.vehicle.TVehicleModel;
import sbz.cardiagnosticbe.exception.VehicleManufacturerException;
import sbz.cardiagnosticbe.exception.VehicleModelException;
import sbz.cardiagnosticbe.model.db.VehicleManufacturer;
import sbz.cardiagnosticbe.model.db.VehicleModel;
import sbz.cardiagnosticbe.repository.VehicleManufacturerRepository;
import sbz.cardiagnosticbe.repository.VehicleModelRepository;

import java.util.List;

@Service
public class VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleManufacturerRepository vehicleManufacturerRepository;

    public VehicleModel getById(Long id) {
        return vehicleModelRepository.getOne(id);
    }

    public void add(TVehicleModel tVehicleModel) {
        VehicleManufacturer vehicleManufacturer = vehicleManufacturerRepository.getOne(tVehicleModel.getVehicleManufacturerId());
        if (vehicleManufacturer == null) {
            throw new VehicleManufacturerException("Manufacturer does not exist");
        }

        if (vehicleModelRepository.findByVehicleManufacturerIdAndModelName(vehicleManufacturer.getId(), tVehicleModel.getName()) != null) {
            throw new VehicleModelException("Model with that name already exists");
        }

        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setModelName(tVehicleModel.getName());
        vehicleModel.setVehicleManufacturer(vehicleManufacturer);
        vehicleModelRepository.save(vehicleModel);
    }

    public List<VehicleModel> getByVehicleManufacturerId(Long vehicleManufacturerId) {
        return vehicleModelRepository.findByVehicleManufacturerId(vehicleManufacturerId);
    }
}
