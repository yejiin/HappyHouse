<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>QnA-답글 수정</h3></b-alert>
      </b-col>
    </b-row>
    <qnA-write-form type="updateA" />
  </b-container>
</template>

<script>
import QnAWriteForm from "./child/QnAWriteForm.vue";

export default {
  name: "QnAUpdate2",
  components: {
    QnAWriteForm,
  },
};
</script>

<style></style>
