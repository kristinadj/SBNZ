<template>
  <b-card-group deck>
    <b-card
      header-tag="header"
      border-variant="light"
      style="max-width: 60rem;"
      align="center"
    >
      <b-container>
        <b-row>
          <b-col> {{ item.failureName }} </b-col>
        </b-row>
        <b-row>
          <b>Severity of the Failure:</b>
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
        <b-row>
          <b-col>
            <a
              class="btn btn-link"
              @click="expandIndicatorsList = !expandIndicatorsList"
              >Indicators ({{ item.indicators.length }})</a
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
        <b-row>
          <b-col>
            <a
              class="btn btn-link"
              @click="expandRepairSteps = !expandRepairSteps"
              >Repair Steps ({{ item.repairSteps.length }})</a
            >
            <div v-if="expandRepairSteps">
              <ol id="repairStepsList">
                <li v-for="repairStep in item.repairSteps" :key="repairStep">
                  {{ repairStep.description }}
                </li>
              </ol>
            </div>
          </b-col>
        </b-row>
      </b-container>
    </b-card>
  </b-card-group>
</template>

<script>
import { authenticationService } from "@/_services";

export default {
  name: "PossibleFailure",
  props: ["item"],
  data() {
    return {
      user: authenticationService.currentUserValue,
      expandIndicatorsList: false,
      expandRepairSteps: false,
    };
  },
  mounted() {
    console.log(this.user);
  },
};
</script>

<style scoped></style>
