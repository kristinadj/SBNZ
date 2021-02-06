<template>
  <div>
    <div class="container center">
      <form @submit.prevent="onSubmit">
        <div class="row">
          <div class="col"></div>
          <div class="col">
            <div class="form-group">
              <h2>Login</h2>
              <label for="username">Username</label>
              <input
                type="text"
                v-model.trim="$v.username.$model"
                name="username"
                class="form-control"
                :class="{ 'is-invalid': submitted && $v.username.$error }"
              />
              <div
                v-if="submitted && !$v.username.required"
                class="invalid-feedback"
              >
                Username is required
              </div>
            </div>
            <div class="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                v-model.trim="$v.password.$model"
                name="password"
                class="form-control"
                :class="{ 'is-invalid': submitted && $v.password.$error }"
              />
              <div
                v-if="submitted && !$v.password.required"
                class="invalid-feedback"
              >
                Password is required
              </div>
            </div>
            <div class="form-group">
              <button class="btn btn-primary" :disabled="loading">
                <span
                  class="spinner-border spinner-border-sm"
                  v-show="loading"
                ></span>
                <span>Login</span>
              </button>
              <router-link to="/register" class="btn btn-link"
                >Register</router-link
              >
            </div>
            <div v-if="error" class="alert alert-danger">{{ error }}</div>
          </div>
          <div class="col"></div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { required } from "vuelidate/lib/validators";

import { router } from "@/_helpers";
import { authenticationService } from "@/_services";

export default {
  data() {
    return {
      username: "",
      password: "",
      submitted: false,
      loading: false,
      returnUrl: "",
      error: "",
    };
  },
  validations: {
    username: { required },
    password: { required },
  },
  created() {
    if (authenticationService.currentUserValue) {
      return router.push("/");
    }

    this.returnUrl = this.$route.query.returnUrl || "/";
  },
  methods: {
    onSubmit() {
      this.error = "";
      this.submitted = true;

      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }

      this.loading = true;
      authenticationService.login(this.username, this.password).then(
        () => router.push(this.returnUrl),
        (error) => {
          this.error = error;
          this.loading = false;
        }
      );
    },
  },
};
</script>
