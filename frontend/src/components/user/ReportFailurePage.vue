<template>
  <div>
    <h1>Report Failure</h1>
    <p>This page can only be accessed by users.</p>
  </div>
</template>

<script>
import { authenticationService } from "@/_services";

export default {
  data() {
    return {
      user: authenticationService.currentUserValue,
    };
  },
};
</script>