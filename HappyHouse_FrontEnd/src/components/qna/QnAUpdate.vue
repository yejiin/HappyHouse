<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>QnA 수정</h3></b-alert>
      </b-col>
    </b-row>
    <qnA-write-form type="modify" />
  </b-container>
</template>

<script>
import QnAWriteForm from "./child/QnAWriteForm.vue";

export default {
  name: "QnAUpdate",
  components: {
    QnAWriteForm,
  },
};
</script>

<style></style>
