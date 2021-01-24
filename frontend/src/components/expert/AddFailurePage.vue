<template>
  <div>
    <h2>Add Failure</h2>
    <form @submit.prevent="onSubmit">
      <div class="form-group">
        <label for="failureName">Name of the failure: </label>
        <input
          type="text"
          v-model="failure.failureName"
          id="failureName"
          class="form-control"
          :class="{ 'is-invalid': submitted && $v.failure.failureName.$error }"
        />
        <div
          v-if="submitted & !$v.failure.failureName.required"
          class="invalid-feedback"
        >
          Failure name is required
        </div>
      </div>
      <hr class="solid" />
      <div class="form-group">
        <label for="vehiclePart">Part of the vehicle:</label>
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
      <hr class="solid" />
      <div class="form-group">
        <label for="isManufactureSpecific">Is Manufacture Specific?</label>
        <div id="isManufactureSpecific">
          <input
            type="radio"
            v-model="failure.isManufactureSpecific"
            value="false"
            id="isManufactureSpecific-no"
          />
          <label for="isManufactureSpecific-no">No</label>
          <input
            type="radio"
            v-model="failure.isManufactureSpecific"
            value="true"
            id="isManufactureSpecific-yes"
          />
          <label for="isManufactureSpecific-yes">Yes</label>
        </div>
      </div>
      <hr class="solid" />
      <div class="form-group">
        <label for="vehicleSubsystem">Subsystem of the vehicle:</label>
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
          <label for="vehicleSubsystem-3">Ignition System or Misfire</label>
        </div>
        <div id="vehicleSubsystem">
          <input
            type="radio"
            v-model="failure.vehicleSubsystem"
            value="4"
            id="vehicleSubsystem-4"
          />
          <label for="vehicleSubsystem-4">Auxiliary Emission Controls</label>
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
      <hr class="solid" />
      <div class="form-group">
        <label for="failureName">How to repair: </label>
        <textarea
          type="textarea"
          v-model="failure.repairSolution"
          id="repairSolution"
          class="form-control"
          :class="{
            'is-invalid': submitted && $v.failure.repairSolution.$error,
          }"
        ></textarea>
        <div
          v-if="submitted && $v.failure.repairSolution.$error"
          class="invalid-feedback"
        >
          <span v-if="!$v.failure.repairSolution.required"
            >An explanation of the repair procedure is required</span
          >
          <span v-if="!$v.failure.repairSolution.minLength"
            >An explanation of the repair procedure must be at least 15
            caharcters</span
          >
        </div>
      </div>
      <div class="form-group">
        <button class="btn btn-primary" :disabled="loading">
          <span
            class="spinner-border spinner-border-sm"
            v-show="loading"
          ></span>
          <span>Add</span>
        </button>
      </div>
      <div v-if="error" class="alert alert-danger">{{ error }}</div>
      <div v-if="success" class="alert alert-success">{{ success }}</div>
    </form>
  </div>
</template>

<script>
import { required, minLength } from "vuelidate/lib/validators";

import { authenticationService, failureService } from "@/_services";

export default {
  data() {
    return {
      user: authenticationService.currentUserValue,
      failure: {
        failureName: "",
        vehiclePart: "POWERTRAIN",
        isManufactureSpecific: false,
        vehicleSubsystem: 1,
        carState: 0,
        repairSolution: "",
        // indicators
      },
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
      repairSolution: { required, minLength: minLength(15) },
    },
  },
  methods: {
    onSubmit(e) {
      console.log(this.failure);
      this.error = "";
      this.success = "";
      this.submitted = true;

      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }

      this.loading = true;
      failureService.add(this.failure).then(
        (response) => {
          this.success = "Successfully added nw failure";
          this.loading = false;
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
