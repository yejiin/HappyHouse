import { apiInstance } from "./index.js";

const api = apiInstance();

function chartData(params, success, fail) {
  console.log(params);
  api.get(`/house/chart`, { params: params }).then(success).catch(fail);
}

export { chartData };
