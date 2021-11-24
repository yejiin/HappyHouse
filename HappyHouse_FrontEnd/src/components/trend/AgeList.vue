<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>트렌드</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert class="text-left" variant="" show><h4>연령별 선호 상권 카테고리</h4></b-alert>
      </b-col>
    </b-row>
    <b-tabs content-class="mt-3" justified active-nav-item-class="font-weight-bold bg-light">
      <b-tab title="20대" v-model="ageValue" value="20" active @row-clicked="viewStore"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="30대" v-model="ageValue" value="30" @row-clicked="viewStore"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="40대" v-model="ageValue" value="40" @row-clicked="viewStore"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="50대" v-model="ageValue" value="50" @row-clicked="viewStore"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="60대" v-model="ageValue" value="60" @row-clicked="viewStore"
        ><p><store></store></p
      ></b-tab>
    </b-tabs>
  </b-container>
</template>

<script>
import Store from "@/components/trend/Store";
import { mapActions } from "vuex";

const trendStore = "trendStore";

export default {
  name: "AgeList",
  components: {
    Store,
  },
  data() {
    return {
      ageValue: "",
    };
  },
  methods: {
    ...mapActions(trendStore, ["getStoreData"]),

    viewStore() {
      this.getStoreData(this.ageValue);
      this.$router.push({
        name: "Store",
        params: { ageGroup: this.ageValue },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
