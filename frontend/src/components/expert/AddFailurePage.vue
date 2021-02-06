<template>
  <div>
    <div class="container">
      <form @submit.prevent="onSubmit">
        <div class="row">
          <div class="col-xl-6">
            <!-- Name -->
            <div class="form-group">
              <label for="failureName"><b>Name of the Failure: </b></label>
              <input
                type="text"
                v-model="failure.failureName"
                id="failureName"
                class="form-control"
                :class="{
                  'is-invalid': submitted && $v.failure.failureName.$error,
                }"
              />
              <div
                v-if="submitted & !$v.failure.failureName.required"
                class="invalid-feedback"
              >
                Failure name is required
              </div>
            </div>

            <!-- Part of the Vehicle -->
            <div class="form-group">
              <label for="vehiclePart"><b>Part of the Vehicle:</b></label>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="failure.vehiclePart"
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
                  v-model="failure.vehiclePart"
                  value="CHASIS"
                  id="chasis"
                />
                <label for="chasis">Chasis Code (Include ABS)</label>
              </div>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="failure.vehiclePart"
                  value="BODY"
                  id="body"
                />
                <label for="body">Body Code (Includes A/C &#38; Air Bag)</label>
              </div>
              <div id="vehiclePart">
                <input
                  type="radio"
                  v-model="failure.vehiclePart"
                  value="NETWORK_VEHICLE_INTEGRATION"
                  id="network_vehicle_integration"
                />
                <label for="network_vehicle_integration"
                  >Network Code (Wiring Bus)</label
                >
              </div>
            </div>

            <!-- Subsystem of the Vehicle -->
            <div class="form-group">
              <label for="vehicleSubsystem"
                ><b>Subsystem of the Vehicle:</b></label
              >
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="failure.vehicleSubsystem"
                  value="1"
                  id="vehicleSubsystem-1"
                />
                <label for="vehicleSubsystem-1">Fuel &#38; Air Metering</label>
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="failure.vehicleSubsystem"
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
                  v-model="failure.vehicleSubsystem"
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
                  v-model="failure.vehicleSubsystem"
                  value="4"
                  id="vehicleSubsystem-4"
                />
                <label for="vehicleSubsystem-4"
                  >Auxiliary Emission Controls</label
                >
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="failure.vehicleSubsystem"
                  value="5"
                  id="vehicleSubsystem-5"
                />
                <label for="vehicleSubsystem-5"
                  >Vehicle Speed Controle &#38; Idle Control System</label
                >
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="failure.vehicleSubsystem"
                  value="6"
                  id="vehicleSubsystem-6"
                />
                <label for="vehicleSubsystem-6">Computer Output Circuit</label>
              </div>
              <div id="vehicleSubsystem">
                <input
                  type="radio"
                  v-model="failure.vehicleSubsystem"
                  value="7"
                  id="vehicleSubsystem-7"
                />
                <label for="vehicleSubsystem-7">Transimission</label>
              </div>
            </div>

            <!-- Severity of the Failure -->
            <div class="form-group">
              <label for="failureSeverity"
                ><b>Severity of the Failure:</b></label
              >
              <div id="failureSeverity">
                <input
                  type="radio"
                  v-model="failure.failureSeverity"
                  value="LOW"
                  id="failureSeverity-low"
                />
                <label for="failureSeverity-low">Low</label>
                <input
                  type="radio"
                  v-model="failure.failureSeverity"
                  value="MEDIUM"
                  id="failureSeverity-medium"
                />
                <label for="failureSeverity-medium">Medium</label>
                <input
                  type="radio"
                  v-model="failure.failureSeverity"
                  value="HIGH"
                  id="failureSeverity-high"
                />
                <label for="failureSeverity-high">High</label>
              </div>
            </div>
          </div>

          <div class="col-xl-6">
            <!-- Is Manufacture Specific? -->
            <div class="form-group">
              <label for="isManufacturerSpecific"
                ><b>Is Manufacturer Specific?</b></label
              >
              <div id="isManufacturerSpecific">
                <input
                  type="radio"
                  v-model="failure.isManufacturerSpecific"
                  :value="false"
                  id="isManufacturerSpecific-no"
                />
                <label for="isManufacturerSpecific-no">No</label>
                <input
                  type="radio"
                  v-model="failure.isManufacturerSpecific"
                  :value="true"
                  id="isManufacturerSpecific-yes"
                />
                <label for="isManufacturerSpecific-yes">Yes</label>
              </div>
            </div>

            <!-- Vehicle Manufacturer -->
            <div class="form-group">
              <div>
                <multiselect
                  v-if="failure.isManufacturerSpecific"
                  v-model="currentVehicle"
                  placeholder="Select Manufacturer"
                  :options="allVehicles"
                  open-direction="bottom"
                  track-by="name"
                  label="name"
                  :close-on-select="true"
                  :internal-search="true"
                  :multiple="false"
                ></multiselect>
              </div>
            </div>

            <!-- Vehicle Model -->
            <div class="form-group">
              <div>
                <multiselect
                  v-if="currentVehicle"
                  v-model="currentVehicleModel"
                  placeholder="Select Model"
                  :options="currentVehicle.vehicleModels"
                  open-direction="bottom"
                  track-by="name"
                  label="name"
                  :close-on-select="true"
                  :internal-search="true"
                  :multiple="false"
                ></multiselect>
              </div>
            </div>

            <!-- Min Year of Production -->
            <div class="form-group">
              <div v-if="failure.isManufacturerSpecific">
                <label for="minVehicleProductionYear"
                  ><b>Year of Production From:</b></label
                >
                <input
                  type="number"
                  v-model="failure.minVehicleProductionYear"
                  id="minVehicleProductionYear"
                  class="form-control"
                  :class="{
                    'is-invalid':
                      submitted && $v.failure.minVehicleProductionYear.$error,
                  }"
                />
                <div
                  v-if="
                    submitted & !$v.failure.minVehicleProductionYear.required
                  "
                  class="invalid-feedback"
                >
                  Year must be between 1990 and 2021
                </div>
              </div>
            </div>

            <!-- Max Year of Production -->
            <div class="form-group">
              <div v-if="failure.isManufacturerSpecific">
                <label for="maxVehicleProductionYear"
                  ><b>Year of Production To:</b></label
                >
                <input
                  type="number"
                  v-model="failure.maxVehicleProductionYear"
                  id="maxVehicleProductionYear"
                  class="form-control"
                  :class="{
                    'is-invalid':
                      submitted && $v.failure.maxVehicleProductionYear.$error,
                  }"
                />
                <div
                  v-if="
                    submitted & !$v.failure.maxVehicleProductionYear.required
                  "
                  class="invalid-feedback"
                >
                  Year must be between 1990 and 2021
                </div>
              </div>
            </div>

            <!-- Indicators -->
            <div class="form-group">
              <label for="indicators"><b>Indicators:</b></label>
              <div id="indicators">
                <multiselect
                  v-model="failure.indicators"
                  placeholder="Select indicators"
                  :options="allIndicators"
                  :close-on-select="false"
                  :internal-search="true"
                  open-direction="bottom"
                  multiple
                ></multiselect>
              </div>
              <br />
              <div>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Describe new indicator..."
                  v-model="newIndicator"
                /><br />
                <input
                  class="btn btn-outline-secondary"
                  value="Add new indicator"
                  @click="addIndicator"
                />
              </div>
            </div>

            <!-- Repair Steps -->
            <div class="form-group">
              <label for="repairSteps"><b>Repair Steps: </b></label>
              <div id="repairSteps">
                <a
                  class="btn btn-link"
                  @click="expandRepairSteps = !expandRepairSteps"
                  >Expand Repair Steps ({{ failure.repairSteps.length }})</a
                >
                <div v-if="expandRepairSteps">
                  <ol id="repairStepsList">
                    <li
                      v-for="repairStep in failure.repairSteps"
                      :key="repairStep"
                    >
                      {{ repairStep }}
                    </li>
                  </ol>
                </div>
                <br />
              </div>
              <div>
                <input
                  type="text"
                  class="form-control"
                  v-model="newRepairStep"
                  placeholder="Describe repair step..."
                /><br />
                <input
                  class="btn btn-outline-secondary"
                  value="Add repair step"
                  @click="addRepairStep"
                />
              </div>
            </div>

            <!-- Submit -->
            <div class="form-group">
              <button
                type="submit"
                name="submitBtn"
                class="btn btn-primary btn-space"
                :disabled="loading"
              >
                <span
                  class="spinner-border spinner-border-sm"
                  v-show="loading"
                ></span>
                <span>Add failure</span>
              </button>
              <router-link to="/failures" class="btn btn-link"
                >Back</router-link
              >
            </div>
            <div v-if="error" class="alert alert-danger">{{ error }}</div>
            <div v-if="success" class="alert alert-success">{{ success }}</div>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { required, between } from "vuelidate/lib/validators";
import Multiselect from "vue-multiselect";

import {
  authenticationService,
  failureService,
  indicatorService,
  vehicleService,
} from "@/_services";

export default {
  components: { Multiselect },
  data() {
    return {
      user: authenticationService.currentUserValue,
      failure: {
        failureName: "",
        vehiclePart: "POWERTRAIN",
        vehicleSubsystem: 1,
        isManufacturerSpecific: false,
        vehicleModelId: null,
        minVehicleProductionYear: null,
        maxVehicleProductionYear: null,
        indicators: [],
        repairSteps: [],
        failureSeverity: "LOW",
      },
      allIndicators: [],
      allVehicles: [],
      currentVehicle: null,
      currentVehicleModel: null,
      newIndicator: "",
      newRepairStep: "",
      expandRepairSteps: false,
      submitted: false,
      loading: false,
      error: "",
      success: "",
    };
  },
  validations: {
    failure: {
      failureName: { required },
      vehiclePart: { required },
      minVehicleProductionYear: { between: between(1990, 2021) },
      maxVehicleProductionYear: { between: between(1990, 2021) },
    },
  },
  methods: {
    onSubmit() {
      console.log(this.failure);
      this.error = "";
      this.success = "";
      this.submitted = true;

      this.$v.$touch();
      if (this.$v.$invalid) {
        console.log("Invalid");
        return;
      }

      if (this.failure.isManufacturerSpecific) {
        this.failure.vehicleModelId = this.currentVehicleModel.id;
      }

      this.loading = true;
      failureService.add(this.failure).then(
        () => {
          this.success = "Successfully added new failure";
          this.loading = false;
          this.submitted = false;

          this.failure = {
            failureName: "",
            vehiclePart: "POWERTRAIN",
            vehicleSubsystem: 1,
            isManufacturerSpecific: false,
            vehicleModelId: null,
            minVehicleProductionYear: null,
            maxVehicleProductionYear: null,
            indicators: [],
            repairSteps: [],
            failureSeverity: "LOW",
          };

          this.currentVehicle = null;
          this.currentVehicleModel = null;
        },
        (error) => {
          this.error = error;
          this.loading = false;
        }
      );
    },
    getAllIndicators() {
      indicatorService
        .getAllAsStrings()
        .then((response) => (this.allIndicators = response));
    },
    getAllVehicles() {
      vehicleService.getAll().then((response) => (this.allVehicles = response));
    },
    addIndicator() {
      if (this.newIndicator.length != 0) {
        this.allIndicators.push(this.newIndicator);
        this.failure.indicators.push(this.newIndicator);
      }
      this.newIndicator = "";
    },
    addRepairStep() {
      if (this.newRepairStep.length != 0) {
        this.failure.repairSteps.push(this.newRepairStep);
      }
      this.newRepairStep = "";
    },
  },
  mounted() {
    this.getAllIndicators();
    this.getAllVehicles();
  },
};
</script>

<style>
input[type="radio"] {
  margin: 0 5px 0 10px;
}

.btn-space {
  margin-right: 10px;
}
</style>
