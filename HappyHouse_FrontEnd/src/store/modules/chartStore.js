import { chartData } from "@/api/chart.js";

const chartStore = {
  namespaced: true,
  state: {
    data: null,
  },
  getters: {},
  mutations: {
    SET_CHART_DATA: (state, data) => {
      state.data = data;
    },
  },
  actions: {
    getChartData: ({ commit }, { lat, lng, name }) => {
      const params = {
        lat,
        lng,
        name,
      };
      console.log(lat);
      chartData(params, ({ data }) => {
        console.log(data);
        commit("SET_CHART_DATA", data);
      }),
        (error) => {
          console.log(error);
        };
    },
  },
};

export default chartStore;
