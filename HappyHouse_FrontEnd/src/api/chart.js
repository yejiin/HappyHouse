import { apiInstance } from "./index.js";

const api = apiInstance();

function chartData(params, success, fail) {
  console.log(params);
  api.get(`/chart`, { params: params }).then(success).catch(fail);
}

export { chartData };
