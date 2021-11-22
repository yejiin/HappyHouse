<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QnA 목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteQuestion } from "@/api/qna";

export default {
  name: "QuestionDelete",
  created() {
    deleteQuestion(this.question.qno, () => {
      this.$router.push({ name: "QnAList" });
    });
  },
};
</script>

<style></style>
