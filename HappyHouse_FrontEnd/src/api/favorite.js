import { apiInstance } from "./index.js";

const api = apiInstance();

function favorite(param, success, fail) {
  api.get(`/house/${param.housename}/${param.userid}`).then(success).catch(fail);
}

function addFavorite(params, success, fail) {
  api
    .post(`/house/${params.housename}/${params.userid}`, {
      gugunname: params.gugunname,
      dong: params.dong,
      jibun: params.jibun,
    })
    .then(success)
    .catch(fail);
}

function cancelFavorite(param, success, fail) {
  api.delete(`/house/${param.housename}/${param.userid}`).then(success).catch(fail);
}

async function favoriteInGugun(param, success, fail) {
  await api.get(`/house/list/${param.gugunname}/${param.housename}/${param.userid}`).then(success).catch(fail);
}

export { favorite, addFavorite, cancelFavorite, favoriteInGugun };
