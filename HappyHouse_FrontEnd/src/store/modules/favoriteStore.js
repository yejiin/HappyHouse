import { favorite, addFavorite, cancelFavorite } from "@/api/favorite.js";

const favoriteStore = {
  namespaced: true,
  state: {
    isFavorite: false,
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
    addFavorite({ commit }, { housename, userid }) {
      const params = {
        housename,
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
  },
};

export default favoriteStore;
