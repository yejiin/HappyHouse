<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>문의</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" size="sm" @click="moveWrite()">문의하기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <!-- <b-col v-if="questions.length"> -->
        <b-table-simple
          hover
          responsive
          id="qnA-list"
          :items="questions"
          :per-page="perPage"
          :current-page="currentPage"
        >
          <b-thead>
            <b-tr>
              <b-th>번호</b-th>
              <b-th>제목</b-th>
              <b-th></b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <qnA-list-row v-for="(question, index) in questions" :key="index" v-bind="question" />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <b-row class="mt-5">
      <b-col>
        <b-pagination
          v-model="currentPage"
          pills
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="qnA-list"
          align="center"
        ></b-pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import QnAListRow from "@/components/qna/child/QnAListRow";
import { listQuestion } from "@/api/qna";

export default {
  name: "QnAList",
  components: {
    QnAListRow,
  },
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      questions: [],
    };
  },
  computed: {
    rows() {
      return this.questions.length;
    },
  },
  created() {
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    listQuestion(
      (response) => {
        this.questions = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "QnAWrite" });
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
