<template>
  <div class="footer">
    <div class="text-dark mt-5 py-5">
      <!-- <div class="col-11"> -->
      <div class="copyright text-center mr-5">
        &copy; copyright {{ year }} SSAFY 6th 송예진 황세원<br />
        All rights reserved.
      </div>
      <!-- </div> -->
    </div>
  </div>
</template>
<script>
export default {
  name: "app-footer",
  data() {
    return {
      year: new Date().getFullYear(),
    };
  },
};
</script>
<style>
.footer {
  background-color: rgb(240, 240, 247);
  /* position: absolute; */
  /* left: 0;
  bottom: 0; */
  /* height: 100px;
  width: 100%;
  padding: 5px 0; */
}
</style>
