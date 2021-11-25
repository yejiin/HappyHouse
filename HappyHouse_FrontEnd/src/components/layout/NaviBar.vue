<template>
  <div class="navmain">
    <b-navbar toggleable="lg" type="" variant="">
      <b-navbar-brand href="#">
        <router-link to="/"> </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-3 mr-5">
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Home' }" class="link">
              <img src="img/logo.png" height="70" width="150" /> </router-link
          ></b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'House' }" class="link"
            ><h4 class="text-white">House</h4></router-link
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'Notice' }" class="link"
            ><h4 class="text-white">Notice</h4></router-link
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'QnA' }" class="link"
            ><h4 class="text-white">Q&A</h4></router-link
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-5">
          <router-link :to="{ name: 'HouseFavorite' }" class="link"><h5>관심목록</h5></router-link>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center">
            <b-avatar
              variant="info"
              src="http://img.danawa.com/prod_img/500000/126/615/img/14615126_1.jpg?shrink=330:330&_v=20210629160554"
            ></b-avatar>
            <span class="text-white">{{ userInfo.username }}({{ userInfo.userid }})님</span>
          </b-nav-item>
          <b-nav-item class="align-self-center"
            ><router-link :to="{ name: 'MyPage' }" class="link align-self-center"
              ><h5 class="text-white">Mypage</h5></router-link
            ></b-nav-item
          >
          <b-nav-item class="link align-self-center" @click.prevent="onClickLogout"
            ><h5 class="text-white">Logout</h5></b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item class="align-self-center">
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <router-link :to="{ name: 'SignUp' }" class="link"
              ><h5 class="text-white">Join</h5></router-link
            ></b-nav-item
          >
          <b-nav-item class="align-self-center">
            <router-link :to="{ name: 'SignIn' }" class="link"
              ><h5 class="text-white">Login</h5></router-link
            ></b-nav-item
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

<style>
.navmain {
  background: linear-gradient(150deg, #a7bbff 15%, #a7bbff 70%);
}
</style>
