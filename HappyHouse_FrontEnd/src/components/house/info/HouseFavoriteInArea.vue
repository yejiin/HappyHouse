<template>
  <div>
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div
          class="swiper-slide"
          v-for="slide in slides"
          :key="slide.id"
          style="height: 200px; background-color: #f1f2eb"
        >
          <b-container>
            <v-row>
              <h4 class="mt-5 text-center">
                <strong>{{ slide.name }}</strong>
              </h4>
            </v-row>
            <br />

            <v-row>
              <div class="text-center">{{ slide.gugunName }} {{ slide.dong }} {{ slide.jibun }}</div>
            </v-row>

            <v-row>
              <div v-if="house.comparable">
                <b-button v-b-modal.modal-1 @click="compareHouse(slide.name, slide.dong)" style="float: right"
                  >비교하기</b-button
                >
              </div>
              <b-icon
                style="float: right"
                icon="exclamation-circle-fill"
                v-else
                v-b-tooltip.hover
                title="비교데이터를 제공하지 않습니다."
              ></b-icon>
            </v-row>
          </b-container>
        </div>
      </div>
      <div class="swiper-pagination"></div>
    </div>

    <b-modal id="modal-1" size="lg" centered title="단지 비교하기" body-bg-variant="light" ok-only>
      <template>
        <div style="margin: 0; width: 100%">
          <b-row>
            <b-col>
              <b-alert show
                ><div>
                  <b-card
                    :title="this.house.name"
                    title-tag="h3"
                    :sub-title="this.compare.cur.address"
                    style="height: 450px"
                  >
                    <br />
                    <b-card-text>
                      <h5>거래 정보</h5>
                      <b-table :items="compare.cur.deal" :fields="fields" class="text-center"> </b-table>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>추가 정보</h5>
                      <div>CCTV 대수 : {{ this.compare.cur.add.cctvCnt }}</div>
                      <div>주차대수(지상) : {{ this.compare.cur.add.parkUCnt }}</div>
                      <div>주차대수(지하) : {{ this.compare.cur.add.parkDCnt }}</div>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="" style="height: 340px">
                    <b-card-text>
                      <h5>시설</h5>
                      <div>부대,복리</div>
                      <p v-if="this.compare.cur.conv.welFac != '0'">: {{ this.compare.cur.conv.welFac }}</p>
                      <p v-else>-</p>
                      <div>편의</div>
                      <p v-if="this.compare.cur.conv.convFac != '0'">: {{ this.compare.cur.conv.convFac }}</p>
                      <p v-else>-</p>
                      <div>교육</div>
                      <p v-if="this.compare.cur.conv.eduFac != '0'">: {{ this.compare.cur.conv.eduFac }}</p>
                      <p v-else>-</p>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>교통</h5>
                      <div>버스정류장 거리</div>
                      <p>: {{ this.compare.cur.traffic.disBus }}</p>
                      <div>지하철호선</div>
                      <p>: {{ this.compare.cur.traffic.subLine }}</p>
                      <div>지하철역</div>
                      <p>: {{ this.compare.cur.traffic.subStation }}</p>
                      <div>지하철역 거리</div>
                      <p>: {{ this.compare.cur.traffic.disSub }}</p>
                    </b-card-text>
                  </b-card>
                </div></b-alert
              >
            </b-col>

            <b-col>
              <b-alert show
                ><div>
                  <b-card
                    :title="this.compName"
                    title-tag="h3"
                    :sub-title="this.compare.comp.address"
                    style="height: 450px"
                  >
                    <br />
                    <b-card-text>
                      <h5>거래 정보</h5>
                      <b-table :items="compare.comp.deal" :fields="fields" class="text-center"> </b-table>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>추가 정보</h5>
                      <div>CCTV 대수 : {{ this.compare.comp.add.cctvCnt }}</div>
                      <div>주차대수(지상) : {{ this.compare.comp.add.parkUCnt }}</div>
                      <div>주차대수(지하) : {{ this.compare.comp.add.parkDCnt }}</div>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="" style="height: 340px">
                    <b-card-text>
                      <h5>시설</h5>
                      <div>부대,복리</div>
                      <p v-if="this.compare.comp.conv.welFac != '0'">: {{ this.compare.comp.conv.welFac }}</p>
                      <p v-else>-</p>
                      <div>편의</div>
                      <p v-if="this.compare.comp.conv.convFac != '0'">: {{ this.compare.comp.conv.convFac }}</p>
                      <p v-else>-</p>
                      <div>교육</div>
                      <p v-if="this.compare.comp.conv.eduFac != '0'">: {{ this.compare.comp.conv.eduFac }}</p>
                      <p v-else>-</p>
                    </b-card-text>
                  </b-card>
                </div>
                <div>
                  <b-card title="" sub-title="">
                    <b-card-text>
                      <h5>교통</h5>
                      <div>버스정류장 거리</div>
                      <p>: {{ this.compare.comp.traffic.disBus }}</p>
                      <div>지하철호선</div>
                      <p>: {{ this.compare.comp.traffic.subLine }}</p>
                      <div>지하철역</div>
                      <p>: {{ this.compare.comp.traffic.subStation }}</p>
                      <div>지하철역 거리</div>
                      <p>: {{ this.compare.comp.traffic.disSub }}</p>
                    </b-card-text>
                  </b-card>
                </div></b-alert
              >
            </b-col>
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
      fields: [
        { key: "date", label: "계약일", tdClass: "tdClass" },
        { key: "price", label: "가격", tdClass: "tdClass" },
        { key: "area", label: "면적", tdClass: "tdClass" },
      ],
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
