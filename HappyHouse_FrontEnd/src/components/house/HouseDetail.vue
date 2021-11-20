<template>
  <div>
    <b-card>
      <b-row>
        <b-col cols="3">
          <b-icon-arrow-left @click="goBack"> </b-icon-arrow-left>
        </b-col>
        <b-col cols="9">OO시 OO동</b-col>
      </b-row>
    </b-card>

    <b-container>
      <div style="overflow: scroll; width: 100%; height: 100vh; padding: 10px">
        <b-card
          no-body
          style="max-width: 20rem"
          img-src="https://placekitten.com/380/200"
          img-alt="Image"
          img-top
        >
          <template #header>
            <h4 class="mb-0">반포 자이아파트</h4>
          </template>

          <b-card-body>
            <b-card-title>매매 3억 9,000 </b-card-title>
            <b-card-sub-title class="mb-2">서초구 반포동</b-card-sub-title>
            <b-card-text> 면적: 25m 층수: 3층 </b-card-text>
          </b-card-body>

          <b-list-group flush>
            <b-list-group-item>Cras justo odio</b-list-group-item>
            <b-list-group-item>Dapibus ac facilisis in</b-list-group-item>
            <b-list-group-item>Vestibulum at eros</b-list-group-item>
          </b-list-group>

          <b-card-body>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </b-card-body>

          <b-card-footer>This is a footer</b-card-footer>
        </b-card>

        <b-card no-body style="max-width: 20rem" class="mt-4">
          <b-card-body>
            <b-card-title>실거래 정보</b-card-title>
          </b-card-body>
        </b-card>

        <b-card no-body style="max-width: 20rem" class="mt-4">
          <b-card-body>
            <b-card-title>비교</b-card-title>
          </b-card-body>
        </b-card>
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
