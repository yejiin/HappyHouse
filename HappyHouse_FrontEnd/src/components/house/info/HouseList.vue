<template>
  <div>
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
      >
        <b-thead head-variant="dark">
          <b-tr>
            <b-th>House List</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <!-- <div > -->
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
          <!-- </div> -->
        </tbody>
      </b-table-simple>
    </div>
    <b-container
      v-else
      class="bv-example-row mt-3"
      style="overflow: auto; height: 67vh"
    >
      <b-row>
        <b-col><b-alert show>목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      numberOfPages: 1,
      houseList: [],
    };
  },
  created() {
    console.log(this.houses);
  },
  // watch: {
  //   houses: function () {
  //     this.houseList = this.houses;
  //     // this.numberOfPages = this.houses.totalCount / this.perPage + 1;
  //   },
  // },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),

    viewDetail(house) {
      this.detailHouse(house);
      // this.$router.push({
      //   name: "HouseDetail",
      //   params: { no: house.aptCode },
      // });
    },
    linkGen(pageNum) {
      return pageNum === 1 ? "?" : `?page=${pageNum}`;
    },
  },
};
</script>

<style>
.scroll-box::-webkit-scrollbar {
  display: none; /* Chrome , Safari , Opera */
}
</style>
