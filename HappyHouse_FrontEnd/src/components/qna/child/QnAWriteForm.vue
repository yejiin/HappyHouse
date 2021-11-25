<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group
            id="subject-group"
            label="제목:"
            label-for="subject"
            description="제목을 입력하세요."
          >
            <b-form-input
              id="subject"
              v-model="question.subject"
              type="text"
              required
              placeholder="제목 입력"
            ></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea
              id="content"
              v-model="question.content"
              placeholder="내용 입력"
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'"
            >문의 등록</b-button
          >
          <b-button type="submit" variant="primary" class="m-1" v-else-if="this.type === 'reply'"
            >답글 등록</b-button
          >
          <b-button type="submit" variant="primary" class="m-1" v-else-if="this.type === 'update'"
            >글수정</b-button
          >
          <b-button type="reset" variant="secondary" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { writeQuestion, getQuestion, modifyQuestion, writeAnswer, updateIsreply } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnAWriteForm",
  data() {
    return {
      question: {
        qno: 0,
        userid: "",
        subject: "",
        content: "",
      },
      answer: {
        ano: 0,
        qno: "",
        subject: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  created() {
    if (this.type === "register") {
      this.question.userid = this.userInfo.userid;
      this.isUserid = true;
    }
    if (this.type === "modify") {
      getQuestion(
        this.$route.params.qno,
        ({ data }) => {
          this.question = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
    if (this.type === "reply") {
      this.question.qno = this.$route.params.qno;
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.question.subject &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      err &&
        !this.question.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else {
        if (this.type === "register") this.registQuestion();
        if (this.type === "update") this.updateQuestion();
        if (this.type === "reply") this.registAnswer();
      }
    },
    onReset(event) {
      event.preventDefault();
      this.question.qno = 0;
      this.question.subject = "";
      this.question.content = "";
      this.$router.push({ name: "QnAList" });
    },
    registQuestion() {
      writeQuestion(
        {
          userid: this.question.userid,
          subject: this.question.subject,
          content: this.question.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    updateQuestion() {
      modifyQuestion(
        {
          qno: this.question.qno,
          subject: this.question.subject,
          content: this.question.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "QnAList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    registAnswer() {
      writeAnswer(
        {
          qno: this.question.qno,
          subject: this.question.subject,
          content: this.question.content,
        },
        ({ data }) => {
          let msg = "답글 작성시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "답글 작성이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          // this.$router.push({ name: "QnAList" });
        },
        (error) => {
          console.log(error);
        }
      );
      updateIsreply(
        {
          qno: this.question.qno,
        },
        ({ data }) => {
          let msg = "답변여부 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "답변여부 수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "QnAList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "QnAList" });
    },
  },
};
</script>

<style></style>
