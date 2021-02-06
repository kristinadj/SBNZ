import { handleResponse, requestOptions } from "@/_helpers";

export const vehicleService = {
  getAll,
  addVehicleManufacturer,
  addVehicleModel,
};

function getAll() {
  return fetch(`/api/vehicleManufacturers`, requestOptions.get()).then(
    handleResponse
  );
}

function addVehicleManufacturer(body) {
  return fetch(`/api/vehicleManufacturers`, requestOptions.post(body)).then(
    handleResponse
  );
}

function addVehicleModel(body) {
  return fetch(`/api/vehicleModels`, requestOptions.post(body)).then(
    handleResponse
  );
}
