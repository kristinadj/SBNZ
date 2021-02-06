import { handleResponse, requestOptions } from "@/_helpers";

export const userService = {
  register
};

function register(body) {
  return fetch(
    `/api/users/register`,
    requestOptions.post(body)
  ).then(handleResponse);
}

