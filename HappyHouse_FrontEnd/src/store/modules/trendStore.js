import { getStoreData } from "@/api/chartstore.js";

const trendStore = {
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
    getStoreData: ({ commit }, ageGroup) => {
      const params = {
        ageGroup,
      };
      getStoreData(params, ({ data }) => {
        console.log(data);
        commit("SET_CHART_DATA", data);
      }),
        (error) => {
          console.log(error);
        };
    },
  },
};

export default trendStore;
