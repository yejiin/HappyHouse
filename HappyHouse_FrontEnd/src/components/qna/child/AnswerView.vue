<template>
  <b-card
    :header-html="`<h4>
          RE: ${subject}</h4><h6>${regtime}</h6>`"
    class="mt-5 mb-2 text-left"
    border-variant="light"
  >
    <b-card-body class="text-left">
      <div v-html="content"></div>
    </b-card-body>
  </b-card>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "AnswerView",
  props: {
    qno: Number,
    ano: Number,
    subject: String,
    content: String,
    regtime: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
