<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>Notice 목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteNotice } from "@/api/notice";

export default {
  name: "NoticeDelete",
  created() {
    deleteNotice(this.notice.nno, () => {
      this.$router.push({ name: "NoticeList" });
    });
  },
};
</script>

<style></style>
