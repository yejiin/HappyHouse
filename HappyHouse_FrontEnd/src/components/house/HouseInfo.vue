<template>
  <div style="width: 100%">
    <house-search-bar />
    <hr />
    <house-list />
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/info/HouseSearchBar";
import HouseList from "@/components/house/info/HouseList";

export default {
  name: "HouseInfo",
  components: {
    HouseSearchBar,
    HouseList,
  },
};
</script>

<style></style>
