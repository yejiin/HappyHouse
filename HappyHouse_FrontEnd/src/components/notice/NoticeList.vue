<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button size="sm" variant="outline-primary" @click="moveWrite()">공지 작성</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          hover
          id="notice-list"
          :items="notices"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          @row-clicked="viewNotice"
        >
        </b-table>
      </b-col>
    </b-row>
    <b-row class="mt-5">
      <b-col>
        <b-pagination
          v-model="currentPage"
          pills
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="notice-list"
          align="center"
        ></b-pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listNotice } from "@/api/notice";

export default {
  name: "NoticeList",
  components: {},
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      notices: [],
      fields: [
        { key: "nno", label: "번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  computed: {
    rows() {
      return this.notices.length;
    },
  },
  created() {
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    listNotice(
      // param,
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

<style>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 250px;
  text-align: left;
}
</style>
