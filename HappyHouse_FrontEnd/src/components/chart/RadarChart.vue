<script>
import { Radar } from "vue-chartjs";
import { mapState } from "vuex";

const chartStore = "chartStore";

export default {
  extends: Radar,
  data() {
    return {
      chartData: {
        labels: ["편의", "의료", "공공시설", "교육", "교통"],
        datasets: [
          {
            label: "현재 주택",
            borderWidth: 0,
            backgroundColor: "rgba(255, 99, 132, 0.4)",
          },
          {
            label: "주택 평균",
            borderWidth: 0,
            backgroundColor: "rgba(54, 162, 235, 0.2)",
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scale: {
          ticks: {
            display: false,
          },
        },
      },
    };
  },
  created() {},
  computed: {
    ...mapState(chartStore, ["data"]),
  },
  mounted() {
    this.chartData.datasets[0].data = [
      this.data.curData.convenience,
      this.data.curData.medical,
      this.data.curData.publicFac,
      this.data.curData.education,
      this.data.curData.traffic,
    ];
    this.chartData.datasets[1].data = [
      this.data.agvData.convenience,
      this.data.agvData.medical,
      this.data.agvData.publicFac,
      this.data.agvData.education,
      this.data.agvData.traffic,
    ];
    this.renderChart(this.chartData, this.options);
  },
};
</script>
