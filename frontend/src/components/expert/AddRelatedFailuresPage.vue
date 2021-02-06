<template>
  <div class="container">
    <div class="row">
      <div class="col-xl-2"></div>
      <div class="col-xl-8">
        <form @submit.prevent="onSubmit">
          <div class="form-group">
            <label for="failures"><b>Generic Failures:</b></label>
            <div id="failures">
              <multiselect
                v-model="selectedFailures"
                placeholder="Select failures"
                :options="allFailures"
                open-direction="bottom"
                track-by="dtcCodeFailureName"
                label="dtcCodeFailureName"
                :close-on-select="false"
                :internal-search="true"
                multiple
              ></multiselect>
            </div>
          </div>
          <div class="form-group">
            <label for="repairSteps"><b>Repair Description: </b></label>
            <div>
              <textarea
                class="form-control"
                v-model="relatedFailures.repairDescription"
                placeholder="Repair description..."
              />
            </div>
          </div>
          <div class="form-group">
            <button
              type="submit"
              name="submitBtn"
              class="btn btn-primary btn-space"
            >
              Add
            </button>
            <router-link to="/failures" class="btn btn-link"
              >Back</router-link
            >
          </div>
          <div v-if="error" class="alert alert-danger">{{ error }}</div>
          <div v-if="success" class="alert alert-success">{{ success }}</div>
        </form>
      </div>
      <div class="col-xl-2"></div>
    </div>
  </div>
</template>

<script>
import Multiselect from "vue-multiselect";

import { authenticationService, failureService } from "@/_services";

export default {
  components: { Multiselect },
  data() {
    return {
      user: authenticationService.currentUserValue,
      relatedFailures: {
        failuresIds: [],
        repairDescription: "",
      },
      selectedFailures: [],
      allFailures: [],
      submitted: false,
      error: "",
      success: "",
    };
  },
  methods: {
    onSubmit() {
      console.log(this.relatedFailures);
      this.error = "";
      this.success = "";
      this.submitted = true;

      // TODO: validations
      this.relatedFailures.failuresIds = this.selectedFailures.map(
        (i) => i.id
      );

      failureService.addRelatedFailures(this.relatedFailures).then(
        () => {
          this.success = "Successfully added";
          this.submitted = false;

          this.relatedFailures = {
            failuresIds: [],
            repairDescription: "",
          };
        },
        (error) => {
          this.error = error;
        }
      );
    },
    getGenericFailures() {
      failureService
        .getFailuresByIsManfucaturerSpecific(false)
        .then((response) => (this.allFailures = response));
    },
  },
  mounted() {
    this.getGenericFailures();
  },
};
</script>
