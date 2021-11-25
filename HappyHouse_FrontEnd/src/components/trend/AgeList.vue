<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>트렌드</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert class="text-left mt-5" variant="" show><h4>연령별 선호 상권 카테고리</h4></b-alert>
      </b-col>
    </b-row>
    <b-tabs content-class="mt-3" justified active-nav-item-class="font-weight-bold bg-light">
      <b-tab title="20대" v-model="ageValue" value="20" active @click="viewStore(20)"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="30대" v-model="ageValue" value="30" @click="viewStore(30)"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="40대" v-model="ageValue" value="40" @click="viewStore(40)"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="50대" v-model="ageValue" value="50" @click="viewStore(50)"
        ><p><store></store></p
      ></b-tab>
      <b-tab title="60대" v-model="ageValue" value="60" @click="viewStore(60)"
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
  created() {
    this.viewStore(20);
  },
  methods: {
    ...mapActions(trendStore, ["getStoreData"]),

    viewStore(ageValue) {
      console.log("연령 선택 ", ageValue);

      this.getStoreData(ageValue);
      // this.$router.push({
      //   name: "Store",
      //   params: { ageGroup: this.ageValue },
      // });
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
