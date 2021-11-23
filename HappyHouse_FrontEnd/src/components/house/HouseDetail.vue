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

        <b-card no-body style="max-width: 20rem" class="mt-4">
          <b-card-body>
            <b-card-title>실거래 정보</b-card-title>
            <b-table striped hover :items="dealInfo.deals" :fields="fields">
            </b-table>
          </b-card-body>
        </b-card>

        <b-card no-body style="max-width: 20rem" class="mt-4">
          <b-card-body>
            <b-card-title>비교</b-card-title>
          </b-card-body>
        </b-card>
      </b-container>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";
const favoriteStore = "favoriteStore";
const memberStore = "memberStore";

export default {
  data() {
    return {
      articles: [],
      fields: [
        { key: "date", label: "계약일" },
        { key: "price", label: "가격" },
        { key: "area", label: "면적" },
        { key: "floor", label: "층수" },
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
        this.addFavorite({
          housename: this.house.name,
          userid: this.userInfo.userid,
        });
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

<style></style>
