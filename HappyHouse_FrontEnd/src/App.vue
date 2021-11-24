<template>
  <div id="app">
    <navi-bar />
    <router-view></router-view>
  </div>
</template>

<script>
import NaviBar from "./components/layout/NaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #42b983;
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
