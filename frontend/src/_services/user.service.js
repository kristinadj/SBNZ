import config from "config";
import { handleResponse, requestOptions } from "@/_helpers";

export const userService = {
  register
};

function register(body) {
  return fetch(
    `${config.apiUrl}/users/register`,
    requestOptions.post(body)
  ).then(handleResponse);
}

