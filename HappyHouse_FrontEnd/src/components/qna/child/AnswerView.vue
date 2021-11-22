<template>
  <b-card
    :header-html="`<h3>
          RE: ${subject}</h3><h6>${regtime}</h6>`"
    class="mb-2 text-left"
    border-variant="dark"
    no-body
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
