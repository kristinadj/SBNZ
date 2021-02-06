<template>
  <b-card-group deck>
    <b-card
      header-tag="header"
      border-variant="light"
      style="max-width: 60rem;"
      align="center"
    >
      <b-container>
        <!-- Name -->
        <b-row>
            <b-col lg="3">
            <b>Failure(s):</b>
          </b-col>
          <b-col>
            <ul class="failures-list">
              <li v-for="name in item.failureNames" :key="name">
                <b>{{ name }}</b>
              </li>
            </ul>
          </b-col>
        </b-row>

        <b-row>
          <b-col lg="3">
            <b>Date and Time:</b>
          </b-col>
          <b-col>
            {{ item.timestamp }}
          </b-col>
        </b-row>

        <!-- Vehicle Information -->
        <b-row>
          <b-col lg="3">
            <b>Vehicle Information:</b>
          </b-col>
          <b-col>
            {{ item.vehicleManufacturer }} {{ item.vehicleModel }},
            {{ item.vehicleProductionYear }}
          </b-col>
        </b-row>

        <!-- Indicators -->
        <b-row>
          <b-col lg="3">
            <b>Indicators:</b>
          </b-col>
          <b-col>
            <a
              class="btn btn-link"
              @click="expandIndicatorsList = !expandIndicatorsList"
              >Expand ({{ item.indicators.length }})</a
            >
            <div v-if="expandIndicatorsList">
              <ul id="indicators">
                <li v-for="indicator in item.indicators" :key="indicator">
                  {{ indicator }}
                </li>
              </ul>
            </div>
          </b-col>
        </b-row>

        <!-- Severity of the Failure -->
        <b-row v-if="item.failureSeverity">
          <b-col lg="3">
            <b>Severity of the Failure:</b>
          </b-col>
          <b-col>
            <div v-if="item.failureSeverity == 'LOW'">
              Low
            </div>
            <div v-if="item.failureSeverity == 'MEDIUM'">
              Medium
            </div>
            <div v-if="item.failureSeverity == 'HIGH'">
              High
            </div>
          </b-col>
        </b-row>

        <!-- Repairment -->
        <b-row>
          <b-col lg="3">
            <b>Suggested:</b>
          </b-col>
          <b-col>
            {{ item.appliedRepairStep }}
          </b-col>
        </b-row>

      </b-container>
    </b-card>
  </b-card-group>
</template>

<script>
import { authenticationService } from "@/_services";

export default {
  name: "ReleatedFailuresProblem",
  props: ["item"],
  data() {
    return {
      user: authenticationService.currentUserValue,
      expandIndicatorsList: false,
    };
  }
};
</script>

<style scoped>
.failures-list {
  list-style-type: none;
}
</style>
