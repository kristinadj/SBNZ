<template>
  <div>
    <b-container>
      <b-row>
        <b-col>
          <!-- <div class="btn-group btn-group-toggle" data-toggle="buttons">
            <label
              class="btn btn-outline-secondary"
              v-bind:class="{ active: manualReport }"
            >
              <input
                type="radio"
                id="manual-option"
                v-model="manualReport"
                :value="true"
                checked
              />
              Manual
            </label>
            <label
              class="btn btn-outline-secondary"
              v-bind:class="{ active: !manualReport }"
            >
              <input
                type="radio"
                id="automatic-option"
                v-model="manualReport"
                :value="false"
              />
              Automatic
            </label>
          </div> -->
        </b-col>
      </b-row>
      <b-row>
        <b-col lg="5">
          <br />
          <div v-if="manualReport">
            <form @submit.prevent="reportFailure">
              <div class="form-group">
                <!-- Indicators -->
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
              <!-- Vehicle Manufacturer -->
              <div class="form-group">
                <div>
                  <multiselect
                    v-model="currentVehicle"
                    placeholder="Select Vehicle Manufacturer"
                    :options="allVehicles"
                    open-direction="bottom"
                    track-by="id"
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
                    track-by="id"
                    label="name"
                    :close-on-select="true"
                    :internal-search="true"
                    :multiple="false"
                  ></multiselect>
                </div>
              </div>

              <div class="form-group">
                <div>
                  <input
                    type="number"
                    v-model="request.vehicleProductionYear"
                    placeholder="Year of Vehicle Production"
                    id="vehicleProductionYear"
                    class="form-control"
                    :class="{
                      'is-invalid':
                        submitted && $v.request.vehicleProductionYear.$error,
                    }"
                  />
                  <div
                    v-if="
                      submitted & !$v.request.vehicleProductionYear.required
                    "
                    class="invalid-feedback"
                  >
                    Year must be between 1990 and 2021
                  </div>
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
                  <span>Report Failure</span>
                </button>
              </div>
            </form>
          </div>
          <div v-if="!manualReport">
            <form @submit.prevent="simulate">
              <div class="form-group">
                <b>Connect the cables to the vehicle!</b>
              </div>
              <!-- Vehicle Manufacturer -->
              <div class="form-group">
                <div>
                  <multiselect
                    v-model="currentVehicle"
                    placeholder="Select Vehicle Manufacturer"
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

              <div class="form-group">
                <div>
                  <input
                    type="number"
                    v-model="request.vehicleProductionYear"
                    placeholder="Year of Vehicle Production"
                    id="vehicleProductionYear"
                    class="form-control"
                    :class="{
                      'is-invalid':
                        submitted && $v.request.vehicleProductionYear.$error,
                    }"
                  />
                  <div
                    v-if="
                      submitted & !$v.request.vehicleProductionYear.required
                    "
                    class="invalid-feedback"
                  >
                    Year must be between 1990 and 2021
                  </div>
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
                  <span>Start</span>
                </button>
              </div>
            </form>
          </div>
        </b-col>
        <b-col lg="7">
          <div v-if="response">
            <div v-if="response.type == 'RELATED_FAILURES'">
              <b>Multiple Failures detected!</b>
              <ReleatedFailuresProblem
                v-bind:item="response"
                :key="response.id"
              ></ReleatedFailuresProblem>
            </div>
            <div v-if="response.type == 'ONE_FAILURE'">
              <b>Detected failure:</b>
              <DetectedFailure
                v-bind:item="response"
                :key="response.id"
              ></DetectedFailure>
            </div>
          </div>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { required, between } from "vuelidate/lib/validators";
import Multiselect from "vue-multiselect";

import {
  authenticationService,
  indicatorService,
  vehicleService,
  failureService,
} from "@/_services";

import ReleatedFailuresProblem from "../detectionResult/DetctedRelatedFailuresProblemComponent";
import DetectedFailure from "../detectionResult/DetectedFailureComponent";

export default {
  components: { Multiselect, ReleatedFailuresProblem, DetectedFailure },
  data() {
    return {
      user: authenticationService.currentUserValue,
      manualReport: true,
      allIndicators: [],
      allVehicles: [],
      currentVehicle: null,
      currentVehicleModel: null,
      selectedIndicators: [],
      request: {
        indicatorsIds: [],
        vehicleModelId: null,
        vehicleProductionYear: null,
      },
      response: null,
      submitted: false,
      loading: false,
    };
  },
  validations: {
    request: {
      vehicleProductionYear: { required, between: between(1990, 2021) },
    },
  },
  methods: {
    reportFailure() {
      this.submitted = true;

      this.$v.$touch();
      if (this.$v.$invalid) {
        console.log("Invalid");
        return;
      }

      this.request.indicatorsIds = this.selectedIndicators.map((i) => i.id);
      this.request.vehicleModelId = this.currentVehicleModel.id;
      console.log(this.request);

      this.loading = true;
      failureService.detect(this.request).then(
        (r) => {
          console.log("Jej radi");
          this.response = r;
          this.loading = false;

          console.log(this.response);
        },
        (error) => {
          console.log(error);
          this.loading = false;
        }
      );
    },
    simulate() {},
    getAllIndicators() {
      indicatorService
        .getAll()
        .then((response) => (this.allIndicators = response));
    },
    getAllVehicles() {
      vehicleService.getAll().then((response) => (this.allVehicles = response));
    },
  },
  mounted() {
    this.getAllIndicators();
    this.getAllVehicles();
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

ul {
  list-style-type: none;
}
</style>
