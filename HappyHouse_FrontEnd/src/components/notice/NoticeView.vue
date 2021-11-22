<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listNotice">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-info" size="sm" @click="moveModifyNotice" class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeNotice">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${notice.nno}.
          ${notice.subject} (${notice.hit})</h3><div><h6><div>${notice.regtime}</h6></div>`"
          class="mb-2 text-left"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getNotice, deleteNotice } from "@/api/notice";

export default {
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    message() {
      if (this.notice.content) return this.notice.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    getNotice(
      this.$route.params.nno,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        console.log("공지 불러오기 에러발생!!", error);
      }
    );
  },
  methods: {
    listNotice() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "NoticeUpdate",
        params: { nno: this.notice.nno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeNotice() {
      if (confirm("정말로 삭제?")) {
        deleteNotice(this.notice.nno, () => {
          this.$router.push({ name: "NoticeList" });
        });
      }
    },
  },
};
</script>

<style></style>
