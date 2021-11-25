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
    // SET_UPDATE_COUNT: (state, user) => {
    //   state.user = user;
    // },
  },
  actions: {
    getStoreData: ({ commit }, ageGroup) => {
      getStoreData(ageGroup, ({ data }) => {
        commit("SET_CHART_DATA", data);
      }),
        (error) => {
          console.log(error);
        };
    },
    // updateCount: ({ commit }, user) => {
    //   updateCount(user, ({ data }) => {
    //     commit("SET_UPDATE_COUNT", data);
    //   }),
    //     (error) => {
    //       console.log(error);
    //     };
    // },
  },
};

export default trendStore;