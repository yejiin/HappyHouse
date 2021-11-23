import { apiInstance } from "./index.js";

const api = apiInstance();

function favorite(param, success, fail) {
  api
    .get(`/favorite/${param.housename}/${param.userid}`)
    .then(success)
    .catch(fail);
}

function addFavorite(param, success, fail) {
  api
    .post(`/favorite/${param.housename}/${param.userid}`)
    .then(success)
    .catch(fail);
}

function cancelFavorite(param, success, fail) {
  api
    .delete(`/favorite/${param.housename}/${param.userid}`)
    .then(success)
    .catch(fail);
}

export { favorite, addFavorite, cancelFavorite };
