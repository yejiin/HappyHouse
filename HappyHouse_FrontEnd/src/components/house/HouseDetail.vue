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
          style="max-width: 20rem"
          img-src="https://cdn.pixabay.com/photo/2017/08/10/05/06/condo-2618421_960_720.jpg"
          img-alt="Image"
          img-top
        >
          <template #header>
            <b-row>
              <b-col cols="10">
                <h4 class="mb-0">
                  {{ house.name }}
                </h4>
              </b-col>
              <b-col cols="2" class="mt-1">
                <b-icon icon="star"></b-icon>
              </b-col>
            </b-row>
          </template>

          <b-card-body>
            <b-card-title v-if="house.dealAmount"
              >매매가 {{ house.dealAmount }}</b-card-title
            >
            <b-card-title v-else-if="house.rentMoney == 0">
              전세 {{ house.deposit }}</b-card-title
            >
            <b-card-title v-else
              >월세 {{ house.deposit }} / {{ house.rentMoney }}</b-card-title
            >
            <b-card-sub-title class="mb-2"
              >{{ house.gugunName }} {{ house.dong }}
              {{ house.jibun }}</b-card-sub-title
            >

            <br />
            <b-list-group flush>
              <b-list-group-item>면적: {{ house.jibun }}㎡ </b-list-group-item>
              <b-list-group-item>층: {{ house.floor }}층 </b-list-group-item>
              <b-list-group-item
                >건축년도: {{ house.buildYear }}년</b-list-group-item
              >
            </b-list-group>
          </b-card-body>

          <!-- <b-card-body>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </b-card-body> -->
        </b-card>

        <!-- <b-card no-body style="max-width: 20rem" class="mt-4">
          <b-card-body>
            <b-card-title>실거래 정보</b-card-title>
            <b-table
              striped
              hover
              :items="articles"
              :fields="fields"
              @row-clicked="viewArticle"
            >
            </b-table>
          </b-card-body>
        </b-card>

        <b-card no-body style="max-width: 20rem" class="mt-4">
          <b-card-body>
            <b-card-title>비교</b-card-title>
          </b-card-body>
        </b-card> -->
      </b-container>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "계약일", tdClass: "tdClass" },
        { key: "subject", label: "가격", tdClass: "tdSubject" },
        { key: "userid", label: "면적", tdClass: "tdClass" },
        { key: "regtime", label: "층수", tdClass: "tdClass" },
      ],
    };
  },
  watch: {
    house: function () {
      console.log(this.house);
    },
  },
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
