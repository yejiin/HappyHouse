import { updateIsreply } from "@/api/qna.js";

const qnaStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {
    UPDATE_ISREPLY: (state, question) => {
      state.question = question;
    },
  },
  actions: {
    updateIsreply({ commit }, question) {
      updateIsreply(
        question,
        (response) => {
          if (response.status === 200) {
            console.log("답변여부 수정 성공!!");
            commit("SET_UPDATE_USER_INFO", user);
          } else {
            console.log("답변여부 수정 실패!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
}

export default qnaStore;
