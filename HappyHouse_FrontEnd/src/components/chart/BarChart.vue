<script>
import { Bar } from "vue-chartjs";
import { mapState } from "vuex";

const trendStore = "trendStore";

export default {
  extends: Bar,
  data() {
    return {
      chartData: {
        labels: ["음식점", "카페", "편의점", "호프/주점", "병원"],
        datasets: [
          {
            label: "연령별 인기 상권 카테고리",
            backgroundColor: "#f87979",
            pointBackgroundColor: "white",
            borderWidth: 1,
            pointBorderColor: "#249EBF",
          },
        ],
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
        legend: {
          display: true,
        },
        responsive: true, // 그래프 넓이
        maintainAspectRatio: false,
      },
    };
  },
  created() {},
  computed: {
    ...mapState(trendStore, ["data"]),
  },
  mounted() {
    this.chartData.datasets[0].data = [
      this.data.food,
      this.data.cafe,
      this.data.convenience,
      this.data.hof,
      this.data.hospital,
    ];
    this.renderChart(this.chartData, this.options);
    console.log(this.chartData);
  },
};
</script>
