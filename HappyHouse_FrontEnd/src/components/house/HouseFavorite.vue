<template>
  <b-container class="bv-example-row mt-4 text-center" style="width: 100%">
    <h3>관심목록</h3>
    <div
      v-if="houses && houses.length != 0"
      class="bv-example-row scroll-box"
      style="overflow: auto; height: 67vh"
    >
      <b-table-simple
        hover
        responsive
        :per-page="perPage"
        :current-page="currentPage"
        class="scroll-box"
      >
        <b-thead head-variant="dark">
          <b-tr>
            <b-th></b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <b-tr v-for="(house, index) in houses" :key="index" v-bind="houses">
            <b-td>
              <b-row @click="viewDetail(house)">
                <b-col cols="4">
                  <b-img
                    style="max-width: 7rem"
                    src="https://cdn.pixabay.com/photo/2017/08/10/05/06/condo-2618421_960_720.jpg"
                    rounded="0"
                    alt="Not rounded image"
                  ></b-img
                ></b-col>
                <b-col cols="8" class="mt-3">
                  <h5>{{ house.name }}</h5>
                  <div>
                    {{ house.gugunName }} {{ house.dong }} {{ house.jibun }}
                  </div>
                  <div>건축년도: {{ house.buildYear }}</div>
                </b-col>
              </b-row>
            </b-td>
          </b-tr>
          <b-container>
            <b-pagination-nav
              :link-gen="linkGen"
              :number-of-pages="numberOfPages"
              use-router
            ></b-pagination-nav>
          </b-container>
        </tbody>
      </b-table-simple>
    </div>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>관심 단지를 추가해주세요.</b-alert></b-col>
      </b-row>
    </b-container>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "Favorite",
  components: {},
  created() {
    this.getFavoriteList(this.userInfo.userid);
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getFavoriteList"]),
  },
};
</script>

<style></style>
