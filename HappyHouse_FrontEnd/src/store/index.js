import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import houseStore from "@/store/modules/houseStore.js";
import memberStore from "@/store/modules/memberStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import qnaStore from "@/store/modules/qnaStore.js";
import favoriteStore from "@/store/modules/favoriteStore.js";
import chartStore from "@/store/modules/chartStore.js";
import trendStore from "@/store/modules/trendStore.js";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
    noticeStore,
    qnaStore,
    favoriteStore,
    chartStore,
    trendStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;
