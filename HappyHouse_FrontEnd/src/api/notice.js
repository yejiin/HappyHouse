import { apiInstance } from "./index.js";

const api = apiInstance();

function listNotice(success, fail) {
  api.get(`/notice`).then(success).catch(fail);
}

function writeNotice(notice, success, fail) {
  api.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function getNotice(nno, success, fail) {
  api.get(`/notice/${nno}`).then(success).catch(fail);
}

function modifyNotice(notice, success, fail) {
  api.put(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function deleteNotice(nno, success, fail) {
  api.delete(`/notice/${nno}`).then(success).catch(fail);
}

export { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice };
