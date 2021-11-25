<template>
  <div>
    <div class="position-relative">
      <!-- shape Hero -->
      <section class="section-shaped my-0">
        <div class="shape shape-style-1 shape-default shape-skew"></div>
        <div class="container shape-container d-flex">
          <div class="row-lg-12 px-0">
            <!-- <div class="col-lg-12"> -->
            <h5 class="display-3 text-secondary py-5"></h5>
            <h5 class="display-3 text-secondary py-5">행복한 보금자리 찾으시나요?<br /></h5>
            <h1 class="text-secondary mb-5">Happy House에서 알아보세요.</h1>
            <!-- </div> -->
          </div>
        </div>
      </section>
    </div>
    <section class="section section-lg mt-3">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-12">
            <div class="row row-grid">
              <div class="col-lg-7">
                <card class="border-0" hover shadow body-classes="py-5">
                  <h4 class="text-secondary">공지사항</h4>
                  <notice-list></notice-list>
                </card>
              </div>
              <div class="col-lg-5">
                <card class="border-0" hover shadow body-classes="py-5">
                  <h4 class="text-secondary">연령대별 핫플레이스</h4>
                  <age-list></age-list>
                </card>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import NoticeList from "../components/notice/NoticeList.vue";
import AgeList from "../components/trend/AgeList.vue";

export default {
  components: { AgeList, NoticeList },
  name: "Main",
};
</script>
<style>
.section-shaped .shape-style-1.shape-default {
  background: linear-gradient(150deg, #a7bbff 15%, #a7bbff 80%);
}
/* .section-shaped .shape-style-1.shape-default {
  background: linear-gradient(150deg, #7795f8 15%, #6772e5 70%, #555abf 94%);
} */
.section-shaped .shape-skew {
  -webkit-transform: skewY(-8deg);
  transform: skewY(-8deg);
  -webkit-transform-origin: 0;
  transform-origin: 0;
}
.section-shaped .shape {
  position: absolute;
  top: 0;
  z-index: -1;
  width: 100%;
  height: 100%;
}

.d-flex {
  display: -webkit-box !important;
  display: -ms-flexbox !important;
  display: flex !important;
}

.section {
  display: block;
}
</style>
