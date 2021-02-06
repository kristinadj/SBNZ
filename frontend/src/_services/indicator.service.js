import { handleResponse, requestOptions } from "@/_helpers";

export const indicatorService = {
  getAll,
  getAllAsStrings,
};

function getAll() {
  return fetch(`/api/indicators`, requestOptions.get()).then(
    handleResponse
  );
}


function getAllAsStrings() {
  return fetch(`/api/indicators/asStrings`, requestOptions.get()).then(
    handleResponse
  );
}
