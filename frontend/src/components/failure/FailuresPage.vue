<template>
  <b-container>
    <b-row>
      <b-col lg="2">
       <router-link to="/add-failure" class="btn btn-outline-primary">New Failure</router-link>
      </b-col>
      <b-col lg="3">
        <router-link to="/related-failures" class="btn btn-outline-primary">Add Related Failures</router-link>
      </b-col>
      <b-col> 
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <ul>
          <li v-for="item in failures" v-bind:key="item.id">
            <failure v-bind:item="item" :key="item.id"></failure>
          </li>
        </ul>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { authenticationService, failureService } from "@/_services";
import failure from "./FailureComponent";

export default {
  components: { failure },
  data() {
    return {
      user: authenticationService.currentUserValue,
      failures: [],
    };
  },
  methods: {
    getAllFailures() {
      failureService
        .getAllFailures()
        .then((response) => (this.failures = response));
    },
  },
  mounted() {
    this.getAllFailures();
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
}
</style>
