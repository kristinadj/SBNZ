<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col">
          <div class="form-group">
            <input
              type="text"
              class="form-control"
              placeholder="Manufacturer Name"
              v-model="newVehicleManufacturer.name"
            />
          </div>
        </div>
        <div class="col">
          <button
            type="submit"
            class="btn btn-outline-primary"
            @click="addVehicleManufacturer"
          >
            <span>Add Manufacturer</span>
          </button>
        </div>
        <div class="col">
          <input
            type="text"
            class="form-control"
            placeholder="Model Name"
            v-model="newVehicleModel.name"
            :disabled="!currentVehicleManufacturer"
          />
        </div>
        <div class="col">
          <button
            type="submit"
            class="btn btn-outline-primary"
            :disabled="!currentVehicleManufacturer"
            @click="addVehicleModel"
          >
            <span>Add Vehicle Model</span>
          </button>
        </div>
      </div>
      <div class="row">
        <div class="col-xl-6">
          <table class="table table-hover">
            <thead>
              <tr>
                <th>Vehicle Manufacturers</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="manufacturer in vehicles"
                :key="manufacturer.id"
                v-on:click="clickVehicleManufacturer(manufacturer)"
                class="clickable"
              >
                <td>{{ manufacturer.name }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col-xl-6">
          <table class="table" v-if="currentVehicleManufacturer">
            <thead>
              <tr>
                <th>{{ currentVehicleManufacturer.name }} - Vehicle Models</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="vehicleModel in currentVehicleManufacturer.vehicleModels"
                :key="vehicleModel.id"
              >
                <td>{{ vehicleModel.name }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { authenticationService, vehicleService } from "@/_services";

export default {
  data() {
    return {
      user: authenticationService.currentUserValue,
      vehicles: [],
      currentVehicleManufacturer: null,
      newVehicleManufacturer: {
        name: "",
      },
      newVehicleModel: {
        vehicleManufacturerId: null,
        name: "",
      },
      errorManufacturer: "",
      errorVehicleModel: "",
    };
  },
  methods: {
    getAllVehicles() {
      vehicleService.getAll().then((response) => (this.vehicles = response));
    },
    clickVehicleManufacturer(clickedManufacturer) {
      this.currentVehicleManufacturer = clickedManufacturer;
    },
    addVehicleManufacturer() {
      if (this.newVehicleManufacturer.name.length != 0) {
        vehicleService.addVehicleManufacturer(this.newVehicleManufacturer).then(
          () => {
            this.getAllVehicles();
            this.newVehicleManufacturer.name = "";
          },
          (error) => {
            console.log(error);
            this.errorManufacturer = error;
          }
        );
      }
    },
    addVehicleModel() {
      this.newVehicleModel.vehicleManufacturerId = this.currentVehicleManufacturer.id;
      console.log(JSON.stringify(this.newVehicleModel));

      if (this.newVehicleModel.name.length != 0) {
        vehicleService.addVehicleModel(this.newVehicleModel).then(
          () => {
            this.vehicles.forEach(v => {
              if (v.id === this.newVehicleModel.vehicleManufacturerId) {
                let newModel = JSON.stringify(this.newVehicleModel);
                v.vehicleModels.push(JSON.parse(newModel));
              }
            })
            this.newVehicleModel.name = "";
          },
          (error) => {
            this.errorVehicleModel = error;
            this.currentVehicleManufacturer = null;
          }
        );
      }
    },
  },
  mounted() {
    this.getAllVehicles();
  },
};
</script>

<style scoped>
.clickable {
  cursor: pointer;
}
</style>
