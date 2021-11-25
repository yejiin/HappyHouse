import { apiInstance } from "./index.js";

const api = apiInstance();

function getStoreData(ageGroup, success, fail) {
  console.log(ageGroup);
  api.get(`/trendstore/${ageGroup}`).then(success).catch(fail);
}

// function updateCount(user, success, fail) {
//   console.log(user);
//   api.put(`/trendstore`, JSON.stringify(user)).then(success).catch(fail);
// }

// export { getStoreData, updateCount };

export { getStoreData };