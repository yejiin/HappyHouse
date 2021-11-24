<template>
  <div>
    <!-- <div class="carousel-container" v-if="favoriteInGugun && favoriteInGugun.length != 0">
      <transition-group class="carousel" tag="div" style="width: 200px">
        <div v-for="slide in slides" class="slide" :key="slide.id">
           <h4>{{ slide.id }} {{ slide.name }}</h4>
     <div>서울시 {{ slide.gugunName }} {{ slide.dong }} {{ slide.jibun }}</div> 

          <b-button v-b-modal.modal-1 @click="compareHouse(slide.id)">{{ slide.id }}</b-button>
        </div>
      </transition-group>
      <div class="carousel-controls">
        <b-row>
          <b-col></b-col>
          <b-col>
            <button class="carousel-controls__button mr-1" @click="previous">&#60;</button>
            <button class="carousel-controls__button ml-1" @click="next">&#62;</button>
          </b-col>
          <b-col></b-col>
        </b-row> 
       </div> -->
    <h2>Carousel with Vue.js</h2>
    <div class="carousel-container">
      <transition-group class="carousel" tag="div">
        <div v-for="slide in slidess" class="slide" :key="slide.id">
          <h4>{{ slide.title }}</h4>
        </div>
      </transition-group>
      <div class="carousel-controls">
        <button class="carousel-controls__button" @click="previous">&#60;</button>
        <button class="carousel-controls__button" @click="next">&#62;</button>
      </div>
    </div>
    <!-- <b-container v-else class="bv-example-row mt-3" style="overflow: auto; height: 67vh">
      <b-row>
        <b-col><b-alert show>해당 지역에 관심 주택이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container> -->

    <b-modal id="modal-1" size="lg" centered title="단지 비교하기" body-bg-variant="light">
      <template style="background-color: black">
        <div style="margin: 0; width: 100%">
          <b-row>
            <b-col>
              <b-alert show
                ><div>
                  <b-card title="현재 보고 있는 단지" sub-title="Card subtitle">
                    <b-card-text>
                      Some quick example text to build on the
                      <em>card title</em> and make up the bulk of the card's content.
                    </b-card-text>

                    <b-card-text>A second paragraph of text in the card.</b-card-text>

                    <a href="#" class="card-link">Card link</a>
                    <b-link href="#" class="card-link">Another link</b-link>
                  </b-card>
                </div>
                <div>
                  <b-card title="Card title" sub-title="Card subtitle">
                    <b-card-text>
                      Some quick example text to build on the
                      <em>card title</em> and make up the bulk of the card's content.
                    </b-card-text>

                    <b-card-text>A second paragraph of text in the card.</b-card-text>

                    <a href="#" class="card-link">Card link</a>
                    <b-link href="#" class="card-link">Another link</b-link>
                  </b-card>
                </div>
                <div>
                  <b-card title="Card title" sub-title="Card subtitle">
                    <b-card-text>
                      Some quick example text to build on the
                      <em>card title</em> and make up the bulk of the card's content.
                    </b-card-text>

                    <b-card-text>A second paragraph of text in the card.</b-card-text>

                    <a href="#" class="card-link">Card link</a>
                    <b-link href="#" class="card-link">Another link</b-link>
                  </b-card>
                </div></b-alert
              >
            </b-col>

            <b-col>
              <b-alert show
                ><div>
                  <b-card title="Card title" sub-title="Card subtitle">
                    <b-card-text>
                      Some quick example text to build on the
                      <em>card title</em> and make up the bulk of the card's content.
                    </b-card-text>

                    <b-card-text>A second paragraph of text in the card.</b-card-text>

                    <a href="#" class="card-link">Card link</a>
                    <b-link href="#" class="card-link">Another link</b-link>
                  </b-card>
                </div>
                <div>
                  <b-card title="Card title" sub-title="Card subtitle">
                    <b-card-text>
                      Some quick example text to build on the
                      <em>card title</em> and make up the bulk of the card's content.
                    </b-card-text>

                    <b-card-text>A second paragraph of text in the card.</b-card-text>

                    <a href="#" class="card-link">Card link</a>
                    <b-link href="#" class="card-link">Another link</b-link>
                  </b-card>
                </div>
                <div>
                  <b-card title="Card title" sub-title="Card subtitle">
                    <b-card-text>
                      Some quick example text to build on the
                      <em>card title</em> and make up the bulk of the card's content.
                    </b-card-text>

                    <b-card-text>A second paragraph of text in the card.</b-card-text>

                    <a href="#" class="card-link">Card link</a>
                    <b-link href="#" class="card-link">Another link</b-link>
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
import { mapState, mapActions } from "vuex";

const favoriteStore = "favoriteStore";
const houseStore = "houseStore";

export default {
  data() {
    return {
      key: 0,
      // slides: null,
      slidess: [
        {
          title: "Slide 1",
          id: 1,
        },
        {
          title: "Slide 2",
          id: 2,
        },
        {
          title: "Slide 3",
          id: 3,
        },
        {
          title: "Slide 4",
          id: 4,
        },
        {
          title: "Slide 5",
          id: 5,
        },
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
    compareHouse(id) {
      console.log(id, this.slides[id].name);
      this.getCompareData({
        name: this.house.name,
        dong: this.house.dong,
        compName: this.slides[id].name,
        compDong: this.slides[id].dong,
      });
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
