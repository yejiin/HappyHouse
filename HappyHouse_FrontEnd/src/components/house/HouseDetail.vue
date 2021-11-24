<template>
  <div>
    <b-card>
      <b-row>
        <b-col cols="3">
          <b-icon-arrow-left @click="goBack"> </b-icon-arrow-left>
        </b-col>
        <b-col cols="7"></b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </b-card>

    <div
      style="
        overflow: auto;
        width: 100%;
        height: 82vh;
        padding-top: 10px;
        padding-left: 20px;
      "
      class="scroll-box"
    >
      <b-container>
        <b-card
          no-body
          style="max-width: 30rem"
          img-src="https://cdn.pixabay.com/photo/2017/08/10/05/06/condo-2618421_960_720.jpg"
          img-alt="Image"
          img-top
        >
          <template #header>
            <b-row>
              <b-col cols="11">
                <h4 class="mb-0">
                  {{ house.name }}
                </h4>
              </b-col>
              <b-col cols="1" class="mt-1">
                <b-icon v-if="!isFavorite" icon="star" @click="like"></b-icon>
                <b-icon
                  v-else
                  icon="star-fill"
                  variant="warning"
                  @click="unlike"
                ></b-icon>
              </b-col>
            </b-row>
          </template>

          <b-card-body>
            <b-card-sub-title class="mb-2"
              >{{ house.gugunName }} {{ house.dong }}
              {{ house.jibun }}</b-card-sub-title
            >

            <b-card-title v-if="dealInfo.range">시세 </b-card-title>
            <b-card-title v-if="dealInfo.range"
              >{{ dealInfo.range.maxAmount }} ~
              {{ dealInfo.range.minAmount }}</b-card-title
            >
          </b-card-body>

          <!-- <b-card-body>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </b-card-body> -->
        </b-card>

        <b-card no-body style="max-width: 30rem" class="mt-4">
          <b-card-body>
            <b-card-title>실거래 정보</b-card-title>
            <b-table striped hover :items="dealInfo.deals" :fields="fields">
            </b-table>
          </b-card-body>
        </b-card>

        <b-card no-body style="max-width: 30rem" class="mt-4">
          <b-card-body>
            <b-card-title>시설 비교 차트</b-card-title>
            <radar-chart></radar-chart>
          </b-card-body>
        </b-card>

        <b-card no-body style="max-width: 30rem" class="mt-4" v-if="isLogin">
          <b-card-body>
            <b-card-title>같은 지역 관심 주택</b-card-title>
            <house-favorite></house-favorite>
          </b-card-body>
        </b-card>
      </b-container>
    </div>
  </div>
</template>

<script>
import RadarChart from "@/components/chart/RadarChart";
import HouseFavorite from "@/components/house/info/HouseFavorite";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";
const favoriteStore = "favoriteStore";
const memberStore = "memberStore";

export default {
  components: {
    RadarChart,
    HouseFavorite,
  },
  data() {
    return {
      articles: [],
      fields: [
        { key: "date", label: "계약일", tdClass: "tdClass" },
        { key: "price", label: "가격", tdClass: "tdClass" },
        { key: "area", label: "면적", tdClass: "tdClass" },
        { key: "floor", label: "층수", tdClass: "tdClass" },
      ],
      likey: true,
      liken: false,
    };
  },
  watch: {
    house: function () {
      console.log("상세화면 house: ", this.house);
    },
    dealInfo: function () {
      console.log("상세화면 deal : ", this.dealInfo);
      console.log(this.house);
    },
  },
  computed: {
    ...mapState(houseStore, ["house", "dealInfo"]),
    ...mapState(favoriteStore, ["isFavorite"]),
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  created() {
    if (this.isLogin) {
      console.log("favorite 여부 판별");
      this.favorite({
        housename: this.house.name,
        userid: this.userInfo.userid,
      });
    }
  },
  methods: {
    ...mapActions(favoriteStore, ["favorite", "addFavorite", "cancelFavorite"]),
    goBack() {
      // this.$router.go(-1);
      this.$router.push({ name: "HouseInfo" });
    },
    like() {
      if (this.isLogin) {
        console.log(this.house.gugunName);
        this.addFavorite({
          housename: this.house.name,
          gugunname: this.house.gugunName,
          jibun: this.house.jibun,
          dong: this.house.dong,
          userid: this.userInfo.userid,
        });
      } else {
        alert("로그인 후 이용 가능합니다.");
      }
    },
    unlike() {
      if (this.isLogin) {
        this.cancelFavorite({
          housename: this.house.name,
          userid: this.userInfo.userid,
        });
      }
    },
  },
};
</script>

<style>
.tdClass {
  height: 20px;
  font-size: 12px;
  text-align: center;
}
</style>
