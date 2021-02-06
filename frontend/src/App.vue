<template>
  <div>
    <!--<link
      href="//netdna.bootstrapcdn.com/bootstrap/4.2.0/css/bootstrap.min.css"
      rel="stylesheet"
    />-->
    <link
      rel="stylesheet"
      href="https://unpkg.com/vue-multiselect@2.1.0/dist/vue-multiselect.min.css"
    />
    <nav
      v-if="currentUser"
      class="navbar navbar-expand-md navbar-light bg-light"
    >
      <img
        class="icon nav-item"
        src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.vectorstock.com%2Fi%2F1000x1000%2F27%2F37%2Fautomotive-diagnostic-repair-icon-vector-1932737.jpg&f=1&nofb=1"
      />
      <div class="nav-brand">
        <b>CarDiagnostic </b>
      </div>
      <div
        class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2"
      >
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <router-link to="/" class="nav-link">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link v-if="isAdmin" to="/vehicles" class="nav-link"
              >Vehicles</router-link
            >
          </li>
          <li class="nav-item">
            <router-link v-if="isAdmin" to="/failures" class="nav-link"
              >Failures</router-link
            >
          </li>
          <li class="nav-item">
            <router-link v-if="!isAdmin" to="/report-failure" class="nav-link"
              >Report failure</router-link
            >
          </li>
          <li class="nav-item">
            <router-link v-if="!isAdmin" to="/failure-history" class="nav-link"
              >Failure history</router-link
            >
          </li>
        </ul>
      </div>
      <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a @click="logout" class="nav-link">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <div class="jumbotron">
      <div class="container">
        <div class="row">
          <div class="col">
            <b-card-group>
              <b-card>
                <router-view></router-view>
              </b-card>
            </b-card-group>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { authenticationService } from "@/_services";
import { router, Role } from "@/_helpers";

export default {
  name: "app",
  data() {
    return {
      currentUser: null,
    };
  },
  computed: {
    isAdmin() {
      return this.currentUser && this.currentUser.role === Role.Admin;
    },
  },
  created() {
    authenticationService.currentUser.subscribe((x) => (this.currentUser = x));
  },
  methods: {
    logout() {
      authenticationService.logout();
      router.push("/login");
    },
  },
};
</script>

<style scoped>
a {
  cursor: pointer;
}

.icon {
  width: 30px;
  height: 30px;
}

.jumbotron {
  background-color: white;
}
</style>
