<template>
  <div id="app">
    <navi-bar />
    <router-view></router-view>
    <app-footer />
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";
import AppFooter from "./components/layout/AppFooter.vue";

export default {
  name: "App",
  components: {
    NaviBar,
    AppFooter,
  },
};
</script>

<style>
a:hover {
  text-decoration: none !important;
  font-weight: bold;
  text-shadow: 5px 5px 5px rgb(255, 223, 164), 0 0 1em rgb(255, 242, 184);
}

a.router-link-exact-active {
  color: #ffcd71;
}

body {
  margin: 0;
  padding: 0;
}

html,
body {
  height: 100%;
}

#app {
  height: 100%;
}
</style>
