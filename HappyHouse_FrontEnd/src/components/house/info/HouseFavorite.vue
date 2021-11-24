<template>
  <div>
    <div
      class="carousel-container"
      v-if="favoriteInGugun && favoriteInGugun.length != 0"
    >
      <transition-group class="carousel" tag="div">
        <div v-for="slide in slides" class="slide" :key="slide.name">
          <div>
            <h4>{{ slide.name }}</h4>
            <div>서울시 서초구 역삼동</div>
            <button>비교하기</button>
          </div>
        </div>
      </transition-group>
      <div class="carousel-controls">
        <b-row>
          <b-col></b-col>
          <b-col>
            <button class="carousel-controls__button mr-1" @click="previous">
              &#60;
            </button>
            <button class="carousel-controls__button ml-1" @click="next">
              &#62;
            </button>
          </b-col>
          <b-col></b-col>
        </b-row>
      </div>
    </div>
    <b-container
      v-else
      class="bv-example-row mt-3"
      style="overflow: auto; height: 67vh"
    >
      <b-row>
        <b-col><b-alert show>해당 지역에 관심 주택이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";

const favoriteStore = "favoriteStore";

export default {
  data() {
    return {
      slides: null,
      // slides: [
      //   {
      //     title: "Slide 1",
      //     id: 1,
      //   },
      //   {
      //     title: "Slide 2",
      //     id: 2,
      //   },
      //   {
      //     title: "Slide 3",
      //     id: 3,
      //   },
      //   {
      //     title: "Slide 4",
      //     id: 4,
      //   },
      //   {
      //     title: "Slide 5",
      //     id: 5,
      //   },
      // ],
    };
  },
  created() {
    this.slides = this.favoriteInGugun;
    console.log(this.favoriteInGugun);
  },
  computed: {
    ...mapState(favoriteStore, ["favoriteInGugun"]),
  },
  methods: {
    next() {
      const first = this.slides.shift();
      this.slides = this.slides.concat(first);
      console.log(first);
      console.log(first, this.slides.concat(first));
    },
    previous() {
      const last = this.slides.pop();
      this.slides = [last].concat(this.slides);
    },
  },
};
</script>

<style>
.carousel {
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.slide {
  flex: 0 0 100%;
  display: flex;
  justify-content: center;
  padding: 50px 0;
  transition: all 0.3s;
  background: #ddd;
  margin: 20px 0;
}

.carousel-controls__button {
  cursor: pointer;
  background: lightsteelblue;
  border: 0;
  color: #fff;
  border-radius: 3px;
  padding: 5px 10px;
  font-size: 18px;
}
</style>
