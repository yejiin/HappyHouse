<template>
  <b-container class="bv-example-row mt-5 text-center">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "Notice",
};
</script>

<style></style>
