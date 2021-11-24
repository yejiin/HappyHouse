<template>
  <div>
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div
          class="swiper-slide"
          v-for="slide in slides"
          :key="slide.id"
          style="height: 200px; background-color: #d3d3d3"
        >
          <!-- <v-row> -->
          <v-row>
            <h4>{{ slide.name }}</h4>
          </v-row>
          <br />

          <v-row>
            <div>{{ slide.gugunName }} {{ slide.dong }} {{ slide.jibun }}</div>
            <b-button v-b-modal.modal-1 @click="compareHouse(slide.name, slide.dong)">비교하기</b-button>
          </v-row>
        </div>
      </div>
      <!-- If we need navigation buttons -->
      <!-- <div class="swiper-button-prev"></div>
      <div class="swiper-button-next"></div> -->
      <div class="swiper-pagination"></div>
    </div>

    <b-modal id="modal-1" size="lg" centered title="단지 비교하기" body-bg-variant="light">
      <template style="background-color: black">
        <div style="margin: 0; width: 100%">
          <b-row>
            <b-col>
              <b-alert show
                ><div>
                  <b-card :title="this.house.name" :sub-title="this.compare.cur.address">
                    <b-card-text>
                      <h5>추가 정보</h5>
                      <div>CCTV 개수 : {{ this.compare.cur.add.cctvCnt }}</div>
                      <div>지상 주차장 : {{ this.compare.cur.add.parkUCnt }}</div>
                      <div>지하 주차장 : {{ this.compare.cur.add.parkDCnt }}</div>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>편의 시설</h5>
                      <div>공공 : {{ this.compare.cur.conv.convFac }}</div>
                      <div>교육 : {{ this.compare.cur.conv.eduFac }}</div>
                      <div>복지 : {{ this.compare.cur.conv.welFac }}</div>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>교통</h5>
                      <div>버스 정류장까지 걸리는 시간 : {{ this.compare.cur.traffic.disBus }}</div>
                      <div>지하철 노선 : {{ this.compare.cur.traffic.subLine }}</div>
                      <div>지하철역 : {{ this.compare.cur.traffic.subStation }}</div>
                      <div>지하철역까지 걸리는 시간 : {{ this.compare.cur.traffic.disSub }}</div>
                    </b-card-text>
                  </b-card>
                </div></b-alert
              >
            </b-col>

            <b-col>
              <b-alert show
                ><div>
                  <b-card :title="this.compName" :sub-title="this.compare.comp.address">
                    <b-card-text>
                      <h5>추가 정보</h5>
                      <div>CCTV 개수 : {{ this.compare.comp.add.cctvCnt }}</div>
                      <div>지상 주차장 : {{ this.compare.comp.add.parkUCnt }}</div>
                      <div>지하 주차장 : {{ this.compare.comp.add.parkDCnt }}</div>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>편의 시설</h5>
                      <div>공공 : {{ this.compare.comp.conv.convFac }}</div>
                      <div>교육 : {{ this.compare.comp.conv.eduFac }}</div>
                      <div>복지 : {{ this.compare.comp.conv.welFac }}</div>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>교통</h5>
                      <div>버스 정류장까지 걸리는 시간 : {{ this.compare.comp.traffic.disBus }}</div>
                      <div>지하철 노선 : {{ this.compare.comp.traffic.subLine }}</div>
                      <div>지하철역 : {{ this.compare.comp.traffic.subStation }}</div>
                      <div>지하철역까지 걸리는 시간 : {{ this.compare.comp.traffic.disSub }}</div>
                    </b-card-text>
                  </b-card>
                </div></b-alert
              ></b-col
            >
          </b-row>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script>
// import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
// import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import { mapState, mapActions } from "vuex";

import "swiper/css/swiper.css";

const favoriteStore = "favoriteStore";
const houseStore = "houseStore";

export default {
  components: {
    // Swiper,
    // SwiperSlide,
  },

  data() {
    return {
      swiperOption: {
        pagination: {
          el: ".swiper-pagination",
        },
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
      },
      slides: null,
      compName: null,
    };
  },
  created() {
    this.slides = this.favoriteInGugun;
    console.log("같은 지역 내 단지 조회", this.favoriteInGugun);
    console.log("비교 데이터", this.compare);
  },
  watch: {
    compare: function () {
      console.log(this.compare);
    },
  },
  computed: {
    ...mapState(houseStore, ["house", "compare"]),
    ...mapState(favoriteStore, ["favoriteInGugun"]),
  },
  methods: {
    ...mapActions(houseStore, ["getCompareData"]),
    compareHouse(name, dong) {
      this.compName = name;
      this.getCompareData({
        name: this.house.name,
        dong: this.house.dong,
        compName: name,
        compDong: dong,
      });
    },
  },
};
</script>

<style></style>
