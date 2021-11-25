import { favorite, addFavorite, cancelFavorite, favoriteInGugun } from "@/api/favorite.js";

const favoriteStore = {
  namespaced: true,
  state: {
    isFavorite: false,
    favoriteInGugun: [],
  },

  getters: {}, // state를 가공

  mutations: {
    SET_IS_FAVORITE: (state, isFavorite) => {
      if (isFavorite === 0) state.isFavorite = false;
      else state.isFavorite = true;
    },
    SET_FAVORITE_Y: (state) => {
      state.isFavorite = true;
    },
    SET_FAVORITE_N: (state) => {
      state.isFavorite = false;
    },
    SET_FAVORITE_IN_GUGUN: (state, data) => {
      state.favoriteInGugun = data;
    },
    CLEAR_FAVORITE_IN_GUGUN: (state) => {
      state.favoriteInGugun = [];
    },
  },
  actions: {
    favorite({ commit }, { housename, userid }) {
      const params = {
        housename,
        userid,
      };
      favorite(
        params,
        ({ data }) => {
          commit("SET_IS_FAVORITE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    addFavorite({ commit }, { housename, gugunname, jibun, dong, userid }) {
      console.log(gugunname);
      const params = {
        housename,
        gugunname,
        jibun,
        dong,
        userid,
      };
      addFavorite(
        params,
        ({ data }) => {
          if (data === "success") {
            console.log("favorite 추가 성공");
            commit("SET_FAVORITE_Y");
          } else {
            console.log("favorite 추가 실패");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    cancelFavorite({ commit }, { housename, userid }) {
      const params = {
        housename,
        userid,
      };
      cancelFavorite(
        params,
        ({ data }) => {
          if (data === "success") {
            console.log("favorite 삭제 성공");
            commit("SET_FAVORITE_N");
          } else {
            console.log("favorite 삭제 실패");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getFavoriteInGugun({ commit }, { gugunname, housename, userid }) {
      const params = {
        gugunname,
        housename,
        userid,
      };
      favoriteInGugun(
        params,
        ({ data }) => {
          commit("SET_FAVORITE_IN_GUGUN", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default favoriteStore;
