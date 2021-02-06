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
          <b-col>
            <ul class="failures-list">
              <li v-for="name in item.relatedFailuresNames" :key="name">
                {{ name }}
              </li>
            </ul>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="2.5">
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
          <b-col lg="2.5">
            <b>How to Repair:</b>
          </b-col>
          <b-col>
            {{ item.repairDescription }}
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
  },
  mounted() {
    console.log(this.user);
  },
};
</script>

<style scoped>
.failures-list {
  list-style-type: none;
}
</style>