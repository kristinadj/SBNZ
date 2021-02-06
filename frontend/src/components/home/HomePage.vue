<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-xl-4">
          <!--<vsa-list>
            <vsa-item initActive>
              <vsa-heading>
                Filter by indicators
              </vsa-heading>
              <vsa-content>
                <form @submit.prevent="filterByIndicators">
                  <div class="form-group">
                    <label><b>Indicators:</b></label>
                    <multiselect
                      v-model="selectedIndicators"
                      placeholder="Select Indicators"
                      :options="allIndicators"
                      track-by="id"
                      label="description"
                      :close-on-select="false"
                      :internal-search="true"
                      open-direction="bottom"
                      multiple
                    ></multiselect>
                  </div>
                  <div class="form-group">
                    <button type="submit" class="btn btn-outline-secondary">
                      <span>Apply filter</span>
                    </button>
                  </div>
                </form>
              </vsa-content>
            </vsa-item>
            <vsa-item initActive>
              <vsa-heading>
                Filter by DTC code parameters
              </vsa-heading>

              <vsa-content>
                
              </vsa-content>
            </vsa-item>
          </vsa-list>-->
          <form @submit.prevent="filterByDtcParams">
            <div class="form-group">
              <label for="vehiclePart"><b>Part of the vehicle:</b></label>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehiclePart"
                  value="POWERTRAIN"
                  id="powertrain"
                />
                <label for="powertrain"
                  >Powertrain Code (Engine &#38; Transimission)</label
                >
              </div>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehiclePart"
                  value="CHASIS"
                  id="chasis"
                />
                <label for="chasis">Chasis Code (Include ABS)</label>
              </div>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehiclePart"
                  value="BODY"
                  id="body"
                />
                <label for="body">Body Code (Includes A/C &#38; Air Bag)</label>
              </div>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehiclePart"
                  value="NETWORK_VEHICLE_INTEGRATION"
                  id="network_vehicle_integration"
                />
                <label for="network_vehicle_integration"
                  >Network Code (Wiring Bus)</label
                >
              </div>
            </div>
            <div class="form-group">
              <label for="isManufacturerSpecific"
                ><b>Is Manufacturer Specific?</b></label
              >
              <div id="isManufacturerSpecific">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.isManufacturerSpecific"
                  :value="false"
                  id="isManufacturerSpecific-no"
                />
                <label for="isManufacturerSpecific-no">No</label>
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.isManufacturerSpecific"
                  :value="true"
                  id="isManufacturerSpecific-yes"
                />
                <label for="isManufacturerSpecific-yes">Yes</label>
              </div>
            </div>
            <div class="form-group">
              <label for="vehicleSubsystem"
                ><b>Subsystem of the vehicle:</b></label
              >
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehicleSubsystem"
                  value="1"
                  id="vehicleSubsystem-1"
                />
                <label for="vehicleSubsystem-1">Fuel &#38; Air Metering</label>
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehicleSubsystem"
                  value="2"
                  id="vehicleSubsystem-2"
                />
                <label for="vehicleSubsystem-2"
                  >Fuel &#38; Air Metering (Injector circuit)</label
                >
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehicleSubsystem"
                  value="3"
                  id="vehicleSubsystem-3"
                />
                <label for="vehicleSubsystem-3"
                  >Ignition System or Misfire</label
                >
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehicleSubsystem"
                  value="4"
                  id="vehicleSubsystem-4"
                />
                <label for="vehicleSubsystem-4"
                  >Auxiliary Emission Controls</label
                >
              </div>
              <div id="vehicleSubsystem">
                <input type="radio" v-model="filterByDtcParamsRequest.vehicleSubsystem" value="5" id="vehicleSubsystem-5" />
                <label for="vehicleSubsystem-5"
                  >Vehicle Speed Controle &#38; Idle Control System</label
                >
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehicleSubsystem"
                  value="6"
                  id="vehicleSubsystem-6"
                />
                <label for="vehicleSubsystem-6">Computer Output Circuit</label>
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="filterByDtcParamsRequest.vehicleSubsystem"
                  value="7"
                  id="vehicleSubsystem-7"
                />
                <label for="vehicleSubsystem-7">Transimission</label>
              </div>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-secondary">
                <span>Apply filter</span>
              </button>
              <button
              class="btn btn-outline-secondary"
              @click="getAllFailures"
            >
              <span>Remove Filters</span>
            </button>
              <div class="form-group">
            
          </div>
            </div>
          </form>
          
        </div>
        <div class="col-xl-8">
          <div v-if="failures.length == 0" >
            <i>No results</i>
          </div>
          <ul>
            <li v-for="item in failures" v-bind:key="item.id">
              <failure v-bind:item="item" :key="item.id"></failure>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import failure from "../failure/FailureComponent";
import "vue-simple-accordion/dist/vue-simple-accordion.css";

import {
  authenticationService,
  failureService,
  indicatorService,
} from "@/_services";

export default {
  components: {
    failure,
  },
  data() {
    return {
      currentUser: authenticationService.currentUserValue,
      selectedIndicators: [],
      allIndicators: [],
      failures: [],
      filterByDtcParamsRequest: {
        vehiclePart: "POWERTRAIN",
        isManufacturerSpecific: false,
        vehicleSubsystem: 1,
      },
    };
  },
  methods: {
    getAllIndicators() {
      indicatorService
        .getAll()
        .then((response) => (this.allIndicators = response));
    },
    getAllFailures() {
      failureService
        .getAllFailures()
        .then((response) => (this.failures = response));
    },
    filterByDtcParams() {
      failureService
        .filterByDtcParams(this.filterByDtcParamsRequest)
        .then((response) => {
          this.failures = response;
        });
    },
  },
  mounted() {
    this.getAllIndicators();
    this.getAllFailures();
  },
};
</script>

<style>
hr.solid {
  border-top: 3px solid #bbb;
}

.vsa-list {
  --vsa-highlight-color: rgb(97, 132, 199);
  --vsa-heading-padding: 0.3rem 1rem;
  --vsa-default-icon-size: 0.4;
}
</style>

<style scoped>
ul {
  list-style-type: none;
}
</style>
