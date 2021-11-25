import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap an BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import store from "./store";
import memberStore from "./store/modules/memberStore";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  async beforeCreate() {
    let token = sessionStorage.getItem("access-token");
    if (memberStore.state.userInfo == null && token) {
      await memberStore.dispatch("getUserInfo", token);
    }
  },
  render: h => h(App)
}).$mount('#app')
