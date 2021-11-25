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
            backgroundColor: "#FFE798",
            pointBackgroundColor: "white",
            borderWidth: 2,
            pointBorderColor: "#249EBF",
            borderColor: "#F6C51F",
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
  created() {
    console.log("barchart created");
  },
  watch: {
    data: function () {
      this.changeAgeGroup();
    },
  },
  computed: {
    ...mapState(trendStore, ["data"]),
  },
  mounted() {
    console.log("mounted");
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
  methods: {
    changeAgeGroup() {
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
  },
};
</script>
