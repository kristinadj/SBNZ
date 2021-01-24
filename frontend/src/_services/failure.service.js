import config from "config";
import { handleResponse, requestOptions } from "@/_helpers";

export const failureService = {
  add
};

function add(body) {
    return fetch(
        `${config.apiUrl}/failure`,
        requestOptions.post(body)
      ).then(handleResponse);
}