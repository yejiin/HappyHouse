import { apiInstance } from "./index.js";

const api = apiInstance();

function listQuestion(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function writeQuestion(question, success, fail) {
  api.post(`/qna`, JSON.stringify(question)).then(success).catch(fail);
}

function getQuestion(qno, success, fail) {
  api.get(`/qna/${qno}`).then(success).catch(fail);
}

function modifyQuestion(question, success, fail) {
  api.put(`/qna`, JSON.stringify(question)).then(success).catch(fail);
}

function deleteQuestion(qno, success, fail) {
  api.delete(`/qna/${qno}`).then(success).catch(fail);
}


// Answer


function writeAnswer(answer, success, fail) {
  api.post(`/qna/answer`, JSON.stringify(answer)).then(success).catch(fail);
}

function getAnswer(qno, success, fail) {
  api.get(`/qna/answer/${qno}`).then(success).catch(fail);
}

function modifyAnswer(answer, success, fail) {
  api.put(`/qna/answer`, JSON.stringify(answer)).then(success).catch(fail);
}

function deleteAnswer(qno, success, fail) {
  api.delete(`/qna/answer/${qno}`).then(success).catch(fail);
}

export { listQuestion, writeQuestion, getQuestion, modifyQuestion, deleteQuestion, writeAnswer, getAnswer, modifyAnswer, deleteAnswer };
