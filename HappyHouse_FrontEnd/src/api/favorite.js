import { apiInstance } from "./index.js";

const api = apiInstance();

function favorite(param, success, fail) {
  api
    .get(`/favorite/${param.housename}/${param.userid}`)
    .then(success)
    .catch(fail);
}

function addFavorite(params, success, fail) {
  api
    .post(`/favorite/${params.housename}/${params.userid}`, {
      gugunname: params.gugunname,
      dong: params.dong,
      jibun: params.jibun,
    })
    .then(success)
    .catch(fail);
}

function cancelFavorite(param, success, fail) {
  api
    .delete(`/favorite/${param.housename}/${param.userid}`)
    .then(success)
    .catch(fail);
}

function favoriteInGugun(param, success, fail) {
  api
    .get(`/favorite/list/${param.gugunname}/${param.housename}/${param.userid}`)
    .then(success)
    .catch(fail);
}

export { favorite, addFavorite, cancelFavorite, favoriteInGugun };
