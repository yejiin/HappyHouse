<template>
  <b-container>
    <b-row class="mt-3 mb-3 text-center">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-row>
    <b-row class="mt-3 mb-3 text-center">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-row>
    <b-row class="mt-3 mb-3 text-center">
      <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.getSidoList();
  },
  methods: {
    ...mapActions(houseStore, ["getSidoList", "getGugunList", "getDongList"]),

    gugunList() {
      this.gugunCode = null;
      if (this.sidoCode) this.getGugunList(this.sidoCode);
    },

    dongList() {
      this.dongCode = null;
      if (this.gugunCode) this.getDongList(this.gugunCode);
    },
  },
};
</script>

<style></style>
