import { BehaviorSubject } from "rxjs";

import config from "config";
import { requestOptions, handleResponse } from "@/_helpers";

const currentUserSubject = new BehaviorSubject(
  JSON.parse(localStorage.getItem("currentUser"))
);

export const authenticationService = {
  login,
  logout,
  currentUser: currentUserSubject.asObservable(),
  get currentUserValue() {
    return currentUserSubject.value;
  },
};

function login(username, password) {
  return fetch(
    `${config.apiUrl}/users/authenticate`,
    requestOptions.post({ username, password })
  )
    .then(handleResponse)
    .then((user) => {
      localStorage.setItem("currentUser", JSON.stringify(user));
      currentUserSubject.next(user);

      return user;
    });
}

function logout() {
  localStorage.removeItem("currentUser");
  currentUserSubject.next(null);
}
