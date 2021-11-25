import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/house/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/house/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/house/dong`, { params: params }).then(success).catch(fail);
}

function aptList(params, success, fail) {
  api.get(`/house/apt`, { params: params }).then(success).catch(fail);
}

function dealList(params, success, fail) {
  api.get(`/house/apt/deal`, { params: params }).then(success).catch(fail);
}

function favoriteList(param, success, fail) {
  api.get(`/house/apt/favorite/${param}`).then(success).catch(fail);
}

function compareData(params, success, fail) {
  console.log(params);
  api.get(`/house/apt/compare`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, aptList, dealList, favoriteList, compareData };
