<template>
  <div>
    <div class="container center">
      <form @submit.prevent="onSubmit">
        <div class="row">
          <div class="col"></div>
          <div class="col">
            <div class="form-group">
              <div class="form-group">
                <label for="name"><b>First Name:</b></label>
                <input
                  type="text"
                  v-model="user.name"
                  name="name"
                  class="form-control"
                  :class="{ 'is-invalid': submitted && $v.user.name.$error }"
                />
                <div
                  v-if="submitted && !$v.user.name.required"
                  class="invalid-feedback"
                >
                  Name is required
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="form-group">
                <label for="lastName"><b>Last Name:</b></label>
                <input
                  type="text"
                  v-model="user.lastName"
                  name="lastName"
                  class="form-control"
                  :class="{
                    'is-invalid': submitted && $v.user.lastName.$error,
                  }"
                />
                <div
                  v-if="submitted && !$v.user.lastName.required"
                  class="invalid-feedback"
                >
                  Last Name is required
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="form-group">
                <label for="username"><b>Username:</b></label>
                <input
                  type="text"
                  v-model="user.username"
                  name="username"
                  class="form-control"
                  :class="{
                    'is-invalid': submitted && $v.user.username.$error,
                  }"
                />
                <div
                  v-if="submitted && !$v.user.username.required"
                  class="invalid-feedback"
                >
                  Username is required
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="form-group">
                <label for="password"><b>Password:</b></label>
                <input
                  type="password"
                  v-model="user.password"
                  name="password"
                  class="form-control"
                  :class="{
                    'is-invalid': submitted && $v.user.password.$error,
                  }"
                />
                <div
                  v-if="submitted && $v.user.password.$error"
                  class="invalid-feedback"
                >
                  <span v-if="!$v.user.password.required"
                    >Password is required</span
                  >
                  <span v-if="!$v.user.password.minLength"
                    >Password must be at least 6 characters</span
                  >
                </div>
              </div>
            </div>
            <div class="form-group">
              <button class="btn btn-success" :disabled="loading">
                <span
                  class="spinner-border spinner-border-sm"
                  v-show="loading"
                ></span>
                <span>Register expert</span>
              </button>
            </div>
            <div v-if="error" class="alert alert-danger">{{ error }}</div>
            <div v-if="success" class="alert alert-success">{{ success }}</div>
         
          </div>
       <div class="col"></div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { required, minLength } from "vuelidate/lib/validators";

import { userService } from "@/_services";

export default {
  data() {
    return {
      user: {
        name: "",
        lastName: "",
        username: "",
        password: "",
        isExpert: true,
      },
      submitted: false,
      loading: false,
      error: "",
      success: "",
    };
  },
  validations: {
    user: {
      name: { required },
      lastName: { required },
      username: { required },
      password: { required, minLength: minLength(6) },
    },
  },
  methods: {
    onSubmit() {
      this.success = "";
      this.error = "";
      this.submitted = true;

      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }

      this.loading = true;
      userService.register(this.user).then(
        () => {
          this.success = "Successfully added a new expert";
          this.loading = false;
          this.submitted = false;

          this.user = {
            name: "",
            lastName: "",
            username: "",
            password: "",
            isExpert: true,
          };
        },
        (error) => {
          this.error = error;
          this.loading = false;
        }
      );
    },
  },
};
</script>

<style scoped>

.center {
  margin: auto;
  width: 100%;
  padding: 10px;
}
</style>