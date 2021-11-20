import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "../../api/member";
import { register } from "@/api/member.js";
import { updateUser } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    registerUser: 0,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_REGISTER_USER: (state) => {
      state.registerUser++;
    },
    SET_UPDATE_USER_INFO: (state, user) => {
      state.userInfo = user;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("회원 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async registerUser({ commit }, user) {
      await register(
        user,
        (response) => {
          if (response.status === 200) {
            console.log("회원 등록 성공!!");
            commit("SET_REGISTER_USER");
          } else {
            console.log("회원 등록 실패!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async updateUser({ commit }, user) {
      await updateUser(
        user,
        (response) => {
          if (response.status === 200) {
            console.log("회원정보 수정 성공!!");
            commit("SET_UPDATE_USER_INFO", user);
          } else {
            console.log("회원정보 수정 실패!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default memberStore;
