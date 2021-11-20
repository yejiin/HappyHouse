import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";

import House from "@/views/House";
import HouseInfo from "@/components/house/HouseInfo";
import HouseDetail from "@/components/house/HouseDetail";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/house",
    name: "House",
    component: House,
    redirect: "/house/info",
    children: [
      {
        path: "info",
        name: "HouseInfo",
        component: HouseInfo,
      },
      {
        path: "detail/:no",
        name: "HouseDetail",
        component: HouseDetail,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
