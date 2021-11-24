import { apiInstance } from "./index.js";

const api = apiInstance();

function getStoreData(ageGroup, success, fail) {
  console.log(ageGroup);
  api.get(`/trendstore/${ageGroup}`).then(success).catch(fail);
}

export { getStoreData };
