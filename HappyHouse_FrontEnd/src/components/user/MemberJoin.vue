<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="5">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="비밀번호 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="이름 입력"
                @keyup.enter="confirm"
              ></b-form-input> </b-form-group
            ><b-form-group label="이메일" label-for="email">
              <b-form-input
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 입력"
                @keyup.enter="confirm"
              ></b-form-input> </b-form-group
            ><b-form-group label="연락처" label-for="phone">
              <b-form-input
                id="phone"
                v-model="user.phone"
                required
                placeholder="연락처 입력"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <hr />
            <b-form-group label="연령대" label-for="ageGroup">
              <b-form-radio v-model="user.ageGroup" value="20">20대</b-form-radio>
              <b-form-radio v-model="user.ageGroup" value="30">30대</b-form-radio>
              <b-form-radio v-model="user.ageGroup" value="40">40대</b-form-radio>
              <b-form-radio v-model="user.ageGroup" value="50">50대</b-form-radio>
              <b-form-radio v-model="user.ageGroup" value="60">60대</b-form-radio>
            </b-form-group>
            <b-form-group label="자주 가는 상권 카테고리" label-for="favStore">
              <b-form-select v-model="favStore" :options="storeOptions"></b-form-select>
            </b-form-group>
            <b-form-group label="관심지역" label-for="favArea">
              <b-form-input
                id="favArea"
                v-model="user.favArea"
                required
                placeholder="관심지역"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              block
              variant="outline-primary"
              class="m-1"
              @click="registerSubmit"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const memberStore = "memberStore";
// const trendStore = "trendStore";

export default {
  name: "MemberJoin",
  data() {
    return {
      user: {
        userid: "",
        password: "",
        username: "",
        email: "",
        phone: "",
        ageGroup: "",
        favStore: "",
      },
      favStore: null,
      storeOptions: [
        { value: null, text: "자주 가는 장소를 선택해주세요" },
        { value: "food", text: "음식점" },
        { value: "cafe", text: "카페" },
        { value: "convenience", text: "편의점" },
        { value: "hof", text: "호프/주점" },
        { value: "hospital", text: "병원" },
      ],
    };
  },

  methods: {
    ...mapActions(memberStore, ["registerUser"]),
    // ...mapActions(trendStore, ["updateCount"]),

    async registerSubmit() {
      this.user.favStore = this.favStore;
      await this.registerUser(this.user);

      // await this.updateCount(this.user);
      alert("회원가입 성공!");
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>
