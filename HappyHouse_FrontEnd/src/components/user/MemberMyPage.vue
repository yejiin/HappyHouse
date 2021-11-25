<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="" show><h3>마이페이지</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <hr class="my-4" />
        <b-container class="mt-4">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left">
              <b-form-group label="아이디" label-for="userid">
                <b-form-input
                  readonly
                  id="userid"
                  v-model="userInfo.userid"
                  required
                  placeholder="아이디 입력"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호" label-for="password">
                <b-form-input
                  type="password"
                  id="password"
                  v-model="userInfo.password"
                  required
                  placeholder="비밀번호 입력"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름" label-for="username">
                <b-form-input
                  id="username"
                  v-model="userInfo.username"
                  required
                  placeholder="이름 입력"
                  @keyup.enter="confirm"
                ></b-form-input> </b-form-group
              ><b-form-group label="이메일" label-for="email">
                <b-form-input
                  id="email"
                  v-model="userInfo.email"
                  required
                  placeholder="이메일 입력"
                  @keyup.enter="confirm"
                ></b-form-input> </b-form-group
              ><b-form-group label="연락처" label-for="phone">
                <b-form-input
                  id="phone"
                  v-model="userInfo.phone"
                  required
                  placeholder="연락처 입력"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <hr />
              <b-form-group label="연령대" label-for="ageGroup">
                <b-form-input
                  id="ageGroup"
                  v-model="userInfo.ageGroup"
                  required
                  placeholder="예: 20/30/40/50/60"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="자주 가는 상권 카테고리">
                <b-form-input
                  id="favStore"
                  v-model="userInfo.favStore"
                  required
                  @keyup.enter="confirm"
                ></b-form-input>
                <b-form-select v-model="favStore" :options="storeOptions"></b-form-select>
              </b-form-group>
              <b-form-group label="관심지역" label-for="favArea">
                <b-form-input
                  id="favArea"
                  v-model="userInfo.favArea"
                  required
                  placeholder="관심지역"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
            </b-form>
          </b-card>
        </b-container>

        <hr class="my-4" />

        <b-button type="button" variant="primary" class="m-1" @click="updateSubmit">수정</b-button>
        <b-button type="button" variant="outline-danger" class="m-1" @click="deleteSubmit"
          >탈퇴</b-button
        >
      </b-col>

      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteUser } from "@/api/member.js";
import { mapState, mapMutations, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
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

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(memberStore, ["updateUser"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    async updateSubmit() {
      this.user.userid = this.userInfo.userid;
      this.user.password = this.userInfo.password;
      this.user.username = this.userInfo.username;
      this.user.email = this.userInfo.email;
      this.user.phone = this.userInfo.phone;
      this.user.ageGroup = this.userInfo.ageGroup;
      this.user.favStore = this.userInfo.favStore;
      await this.updateUser(this.user);

      alert("수정 완료!");
      this.$router.push({ name: "MyPage" });
    },
    deleteSubmit() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        deleteUser(this.userInfo.userid, () => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          if (this.$route.path != "/") {
            this.$router.push({ name: "Home" });
          }
        });
      }
    },
  },
};
</script>

<style></style>
