<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>문의</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listQuestion">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-primary" size="sm" @click="moveRegisterAnswer" class="mr-2">
          답글 작성
        </b-button>
        <b-button variant="outline-secondary" size="sm" @click="moveModifyAnswer" class="mr-2"
          >답글 수정</b-button
        >
        <b-button
          variant="outline-secondary"
          size="sm"
          @click="moveModifyQuestion"
          class="mr-2 ml-2"
          >문의 수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeQuestion">문의 삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h4>${question.qno}.
          ${question.subject}</h4><div><h6>ID: ${question.userid}<br>${question.regtime}</h6></div>`"
          class="mb-2 text-left"
          border-variant="light"
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col v-if="answer">
        <answer-view v-if="show" v-bind="answer" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getQuestion, deleteQuestion, getAnswer } from "@/api/qna";
import AnswerView from "@/components/qna/child/AnswerView.vue";

export default {
  data() {
    return {
      question: {},
      answer: {},
      show: true,
      qno: null,
    };
  },
  components: {
    AnswerView,
  },

  computed: {
    message() {
      if (this.question.content) return this.question.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQuestion(
      this.$route.params.qno,
      (response) => {
        this.question = response.data;
      },
      (error) => {
        console.log("문의글 불러오기 에러발생!!", error);
      }
    );
    getAnswer(
      this.$route.params.qno,
      (response) => {
        this.answer = response.data;
      },
      (error) => {
        console.log("답글 불러오기 에러발생!!", error);
      }
    );
  },
  methods: {
    listQuestion() {
      this.$router.push({ name: "QnAList" });
    },
    moveModifyQuestion() {
      this.$router.replace({
        name: "QnAUpdate",
        params: { qno: this.question.qno },
      });
    },
    moveModifyAnswer() {
      this.$router.replace({
        name: "QnAUpdate2",
        params: { qno: this.question.qno },
      });
    },
    moveRegisterAnswer() {
      this.$router.replace({
        name: "AnswerWrite",
        params: { qno: this.question.qno },
      });
    },
    removeQuestion() {
      if (confirm("정말로 삭제?")) {
        deleteQuestion(this.question.qno, () => {
          this.$router.push({ name: "QnAList" });
        });
      }
    },
  },
};
</script>

<style></style>
