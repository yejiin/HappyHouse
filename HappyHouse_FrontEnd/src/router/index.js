import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";

import House from "@/views/House";
import HouseInfo from "@/components/house/HouseInfo";
import HouseDetail from "@/components/house/HouseDetail";

import Member from "@/views/Member.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";
import MemberFavor from "@/components/user/MemberFavor.vue";

import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeView from "@/components/notice/NoticeView.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";
import NoticeDelete from "@/components/notice/NoticeDelete.vue";

import QnA from "@/views/QnA.vue";
import QnAList from "@/components/qna/QnAList.vue";
import QnAWrite from "@/components/qna/QnAWrite.vue";
import AnswerWrite from "@/components/qna/AnswerWrite.vue";
import QnAView from "@/components/qna/QnAView.vue";
import QnAUpdate from "@/components/qna/QnAUpdate.vue";
import QnADelete from "@/components/qna/QnADelete.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};

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
        path: "detail",
        name: "HouseDetail",
        component: HouseDetail,
      },
    ],
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    children: [
      {
        path: "singin",
        name: "SignIn",
        component: MemberLogin,
      },
      {
        path: "singup",
        name: "SignUp",
        component: MemberJoin,
      },
      {
        path: "favor",
        name: "Favor",
        component: MemberFavor,
      },
      {
        path: "mypage",
        name: "MyPage",
        beforeEnter: onlyAuthUser,
        component: MemberMyPage,
      },
    ],
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "write",
        name: "NoticeWrite",
        beforeEnter: onlyAuthUser,
        component: NoticeWrite,
      },
      {
        path: "view/:nno",
        name: "NoticeView",
        beforeEnter: onlyAuthUser,
        component: NoticeView,
      },
      {
        path: "update/:nno",
        name: "NoticeUpdate",
        beforeEnter: onlyAuthUser,
        component: NoticeUpdate,
      },
      {
        path: "delete/:nno",
        name: "NoticeDelete",
        component: NoticeDelete,
      },
    ],
  },
  {
    path: "/qna",
    name: "QnA",
    component: QnA,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnAList",
        component: QnAList,
      },
      {
        path: "write",
        name: "QnAWrite",
        beforeEnter: onlyAuthUser,
        component: QnAWrite,
      },
      {
        path: "view/:qno",
        name: "QnAView",
        beforeEnter: onlyAuthUser,
        component: QnAView,
      },
      {
        path: "update/:qno",
        name: "QnAUpdate",
        beforeEnter: onlyAuthUser,
        component: QnAUpdate,
      },
      {
        path: "delete/:qno",
        name: "QnADelete",
        component: QnADelete,
      },
      {
        path: "answer",
        name: "AnswerWrite",
        component: AnswerWrite,
      },
    ],
  },
  {
    path: "*",
    redirect: "/",
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
