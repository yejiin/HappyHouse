<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()">공지 작성</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table hover :items="notices" :fields="fields" @row-clicked="viewNotice"> </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listNotice } from "@/api/notice";

export default {
  name: "NoticeList",
  components: {
    // BoardListRow,
  },
  data() {
    return {
      notices: [],
      fields: [
        { key: "nno", label: "번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listNotice(
      param,
      (response) => {
        this.notices = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },
    viewNotice(notice) {
      console.log(notice.nno);
      this.$router.push({
        name: "NoticeView",
        params: { nno: notice.nno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
