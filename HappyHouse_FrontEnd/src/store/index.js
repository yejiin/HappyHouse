import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import houseStore from "@/store/modules/houseStore.js";
import memberStore from "@/store/modules/memberStore.js";

Vue.use(Vuex);



const store = new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;
