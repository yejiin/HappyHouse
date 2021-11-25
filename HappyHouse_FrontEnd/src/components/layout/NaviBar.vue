<template>
  <div>
    <b-navbar toggleable="lg" type="" variant="light">
      <b-navbar-brand href="#">
        <router-link to="/"> </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Home' }" class="link"
              ><h3><b-icon icon="house" font-scale="1"></b-icon> Happy House</h3></router-link
            ></b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'House' }" class="link"><h5>House</h5></router-link>
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'Notice' }" class="link"><h5>Notice</h5></router-link>
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'QnA' }" class="link"><h5>QnA</h5></router-link>
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'Trend' }" class="link"><h5>Trend</h5></router-link>
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'HouseFavorite' }" class="link"><h5>관심목록</h5></router-link>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center">
            <!-- <b-avatar
              variant="primary"
              v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
<<<<<<< HappyHouse_FrontEnd/src/components/layout/NaviBar.vue
            ></b-avatar
            >{{ userInfo.username }}({{ userInfo.userid }})님
            환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'MyPage' }"
              class="link align-self-center"
              >Mypage</router-link
            ></b-nav-item
          >
          <b-nav-item
            class="link align-self-center"
            @click.prevent="onClickLogout"
            >Logout</b-nav-item
=======
            ></b-avatar> -->
            <b-avatar
              variant="info"
              src="http://img.danawa.com/prod_img/500000/126/615/img/14615126_1.jpg?shrink=330:330&_v=20210629160554"
            ></b-avatar>
            {{ userInfo.username }}({{ userInfo.userid }})님
          </b-nav-item>
          <b-nav-item class="align-self-center"
            ><router-link :to="{ name: 'MyPage' }" class="link align-self-center"
              ><h6>Mypage</h6></router-link
            ></b-nav-item
          >
          <b-nav-item class="link align-self-center" @click.prevent="onClickLogout"><h6>Logout</h6></b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item class="align-self-center">
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <router-link :to="{ name: 'SignUp' }" class="link"><h6>Join</h6></router-link></b-nav-item
          >
          <b-nav-item class="align-self-center">
            <router-link :to="{ name: 'SignIn' }" class="link"><h6>Login</h6></router-link></b-nav-item
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style></style>
