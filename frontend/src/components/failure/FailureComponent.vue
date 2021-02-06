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
          <b-col lg="4">
            <b>Name of the Failure:</b>
          </b-col>
          <b-col> {{ item.dtcCode }} - {{ item.name }} </b-col>
        </b-row>
        <b-row>
          <b-col lg="4">
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
        <b-row>
          <b-col lg="4">
            <b>Manufacturer Specific:</b>
          </b-col>
          <b-col>
            <div v-if="item.isManufacturerSpecific">
              Yes
            </div>
            <div v-else>
              No
            </div>
          </b-col>
        </b-row>
        <b-row v-if="item.isManufacturerSpecific">
          <b-col lg="4">
            <b>Vehicle Information:</b>
          </b-col>
          <b-col>
            {{ item.vehicleManufacturer }} {{ item.vehicleModel }},
            {{ item.minVehicleProductionYear }} -
            {{ item.maxVehicleProductionYear }}
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="4">
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
        <b-row>
          <b-col lg="4">
            <b>Repair Steps:</b>
          </b-col>
          <b-col>
            <a
              class="btn btn-link"
              @click="expandRepairSteps = !expandRepairSteps"
              >Expand Repair Steps ({{ item.repairSteps.length }})</a
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
        <b-row v-if="user.role == 'EXPERT'">
          <b-col lg="5"></b-col>
          <b-col lg="1.5">
            <input class="btn btn-outline-secondary button" value="Edit" />
          </b-col>
          <b-col lg="1.5">
            <input
              class="btn btn-outline-danger button"
              value="Remove"
              @click="removeFailure"
            />
          </b-col>
        </b-row>
      </b-container>
    </b-card>
  </b-card-group>
</template>

<script>
import { authenticationService, failureService } from "@/_services";

export default {
  name: "failure",
  props: ["item"],
  data() {
    return {
      user: authenticationService.currentUserValue,
      expandRepairSteps: false,
      expandIndicatorsList: false,
    };
  },
  methods: {
    removeFailure() {
      failureService.removeFailure(this.item.id).then(
        () => {},
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.button {
  display: inline-block;
  min-width: 50px;
  width: 80px;
  padding: 5px 10px;
}
</style>
