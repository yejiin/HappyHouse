<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>문의하기</h3></b-alert>
      </b-col>
    </b-row>
    <qnA-write-form type="register" />
  </b-container>
</template>

<script>
import QnAWriteForm from "./child/QnAWriteForm.vue";

export default {
  name: "QnAWrite",
  components: {
    QnAWriteForm,
  },
};
</script>

<style></style>
