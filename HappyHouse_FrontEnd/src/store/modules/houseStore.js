import { sidoList, gugunList, dongList, aptList, dealList, favoriteList, compareData } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도 선택하세요" }],
    guguns: [{ value: null, text: "구/군 선택하세요" }],
    dongs: [{ value: null, text: "읍/면/동 선택하세요" }],
    houses: null,
    house: null,
    sido: null,
    gugun: null,
    dealInfo: null,
    compare: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, { data, sidoCode }) => {
      state.sido = state.sidos.filter((sido) => sido.value == sidoCode);

      data.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도 선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군 선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동 선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_DEAL_INFO: (state, dealInfo) => {
      state.dealInfo = dealInfo;
    },
    SET_FAVORITE_LIST: (state, favorites) => {
      state.houses = favorites;
    },
    CLEAR_House_LIST: (state) => {
      state.houses = [];
    },
    SET_COMPARE_DATA: (state, compareData) => {
      state.compare = compareData;
    },
  },

  actions: {
    getSidoList: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugunList: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", { data, sidoCode });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDongList: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptList: ({ commit }, { dong, gugunCode }) => {
      const params = {
        dong,
        gugunCode,
      };
      aptList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      commit("SET_DETAIL_HOUSE", house);
    },
    getDealList: ({ commit }, { dong, jibun }) => {
      const params = {
        dong,
        jibun,
      };
      dealList(
        params,
        ({ data }) => {
          commit("SET_DEAL_INFO", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getFavoriteList: ({ commit }, userid) => {
      favoriteList(
        userid,
        ({ data }) => {
          commit("SET_FAVORITE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getCompareData: ({ commit }, { name, dong, compName, compDong }) => {
      const params = {
        name,
        dong,
        compName,
        compDong,
      };
      compareData(
        params,
        ({ data }) => {
          commit("SET_COMPARE_DATA", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
