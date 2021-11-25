<template>
  <!-- 92.85% -->
  <div class="scroll-box" style="overflow: auto">
    <b-row>
      <b-col cols="3"><router-view></router-view></b-col>
      <b-col cols="9"><house-map /></b-col>
    </b-row>
  </div>
</template>

<script>
import HouseMap from "@/components/house/HouseMap";

export default {
  name: "House",
  components: {
    HouseMap,
  },
};
</script>

<style>
.scroll-box::-webkit-scrollbar {
  display: none; /* Chrome , Safari , Opera */
}
</style>
