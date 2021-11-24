<template>
  <div>
    <b-card no-body style="max-width: 100rem" class="mt-4">
      <b-card-body>
        <b-card-title>상권 차트</b-card-title>
        <bar-chart></bar-chart>
      </b-card-body>
    </b-card>
  </div>
</template>
<script>
import BarChart from "@/components/chart/BarChart";

export default {
  name: "Store",
  components: {
    BarChart,
  },
};
</script>

<style></style>
