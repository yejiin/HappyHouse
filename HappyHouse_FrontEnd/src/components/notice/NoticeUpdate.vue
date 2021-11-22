<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>Notice 수정</h3></b-alert>
      </b-col>
    </b-row>
    <notice-write-form type="modify" />
  </b-container>
</template>

<script>
import NoticeWriteForm from "./child/NoticeWriteForm.vue";

export default {
  name: "NoticeUpdate",
  components: {
    NoticeWriteForm,
  },
};
</script>

<style></style>
