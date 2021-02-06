<template>
  <div>
    <h1>Failure History</h1>
    <b-container>
      <b-row>
        <b-col>
          <div class="btn-group btn-group-toggle" data-toggle="buttons">
            <label
              class="btn btn-outline-secondary"
              v-bind:class="{ active: sortRecently }"
            >
              <input
                type="radio"
                id="manual-option"
                v-model="sortRecently"
                :value="true"
                @change="onSortChange($event)"
                checked
              />
              Recent first
            </label>
            <label
              class="btn btn-outline-secondary"
              v-bind:class="{ active: !sortRecently }"
            >
              <input
                type="radio"
                id="automatic-option"
                v-model="sortRecently"
                :value="false"
                @change="onSortChange($event)"
              />
              Older first
            </label>
          </div>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <ul>
            <li v-for="item in failures" v-bind:key="item.timestamp">
              <FailureInHistory
                v-bind:item="item"
                :key="item.timestamp"
              ></FailureInHistory>
            </li>
          </ul>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { authenticationService, failureService } from "@/_services";
import FailureInHistory from "../failure/FailureInHistoryComponent";

export default {
  components: { FailureInHistory },
  data() {
    return {
      user: authenticationService.currentUserValue,
      failures: [],
      sortRecently: true
    };
  },
  methods: {
    getHistoryFailures() {
      failureService
        .getHistoryFailures(this.sortRecently)
        .then((response) => (this.failures = response));
    },
    onSortChange() {
      failureService
        .getHistoryFailures(this.sortRecently)
        .then((response) => (this.failures = response));
    }
  },
  mounted() {
    this.getHistoryFailures();
  },
};
</script>

<style scoped>
ul {
  list-style-type: none;
}
</style>
