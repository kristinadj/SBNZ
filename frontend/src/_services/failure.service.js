import { handleResponse, requestOptions } from "@/_helpers";

export const failureService = {
  add,
  getFailuresByIsManfucaturerSpecific,
  addRelatedFailures,
  getAllFailures,
  removeFailure,
  detect,
  getHistoryFailures,
  filterByDtcParams
};

function add(body) {
  return fetch(`/api/failures`, requestOptions.post(body)).then(handleResponse);
}

function getAllFailures() {
  return fetch(`/api/failures`, requestOptions.get()).then(handleResponse);
}

function getHistoryFailures(sortRecently) {
  let params = {
    sortRecently: sortRecently,
  };

  let queryParams = Object.keys(params)
    .map((k) => encodeURIComponent(k) + "=" + encodeURIComponent(params[k]))
    .join("&");

  return fetch(`/api/failureHistory?` + queryParams, requestOptions.get()).then(handleResponse);
}

function removeFailure(id) {
  return fetch(`/api/failures/` + id, requestOptions.delete()).then(handleResponse);
}

function getFailuresByIsManfucaturerSpecific(isManufacturerSpecific) {
  let params = {
    isManufacturerSpecific: isManufacturerSpecific,
  };

  let queryParams = Object.keys(params)
    .map((k) => encodeURIComponent(k) + "=" + encodeURIComponent(params[k]))
    .join("&");

  return fetch(`/api/failures/byIsManufacturerSpecific?` + queryParams, requestOptions.get()).then(
    handleResponse
  );
}

function addRelatedFailures(body) {
  return fetch(`/api/relatedFailures`, requestOptions.post(body)).then(
    handleResponse
  );
}

function detect(body) {
  return fetch(`/api/failures/detect`, requestOptions.post(body)).then(
    handleResponse
  );
}

function filterByDtcParams(body) {
  return fetch(`/api/failures/byDtc`, requestOptions.post(body)).then(
    handleResponse
  );
}