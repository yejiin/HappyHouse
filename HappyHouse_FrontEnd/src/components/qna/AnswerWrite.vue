<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>문의 답변</h3></b-alert>
      </b-col>
    </b-row>
    <qnA-write-form type="reply" />
  </b-container>
</template>

<script>
import QnAWriteForm from "./child/QnAWriteForm.vue";

export default {
  name: "AnswerWrite",
  components: {
    QnAWriteForm,
  },
};
</script>

<style></style>
