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
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchHouse"
      ></b-form-select>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

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
    this.CLEAR_SIDO_LIST();
    this.getSidoList();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSidoList",
      "getGugunList",
      "getDongList",
      "getHouseList",
      "getHouesApiList",
      "setAddress",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugunList(this.sidoCode);
    },

    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDongList(this.gugunCode);
    },

    searchHouse() {
      if (this.dongCode) {
        for (let dong of this.dongs) {
          if (dong.value == this.dongCode) {
            this.getHouseList({
              dong: dong.text,
              gugunCode: this.gugunCode,
              page: 1,
            });
            break;
          }
        }
      }
    },
  },
};
</script>

<style></style>
