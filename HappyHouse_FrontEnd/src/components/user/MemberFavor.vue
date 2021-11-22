<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>추가 정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="5">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left mt-3 mb-3">
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

export default {
  name: "MemberFavor",
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
        // favArea: "",
      },
      favStore: null,
      storeOptions: [
        { value: null, text: "편의점" },
        { value: "a", text: "음식점" },
        { value: "b", text: "카페" },
        { value: "c", text: "호프/주점" },
      ],
    };
  },
  created() {
    this.user.userid = this.$route.params.userid;
    this.user.password = this.$route.params.password;
    this.user.username = this.$route.params.username;
    this.user.email = this.$route.params.email;
    this.user.phone = this.$route.params.phone;
    this.user.ageGroup = this.$route.params.ageGroup;
    this.user.favStore = this.$route.params.favStore;
    this.isUserid = true;
  },
  methods: {
    ...mapActions(memberStore, ["registerUser"]),

    async registerSubmit() {
      await this.registerUser(this.user);
      alert("회원가입 성공!");
      this.$router.push({ name: "SignIn" });
    },
  },
};
</script>

<style></style>
