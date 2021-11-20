<template>
  <div style="width: 100%">
    <b-table-simple hover responsive>
      <b-thead head-variant="dark">
        <b-tr>
          <b-th>매물 리스트</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <b-tr
          v-for="(article, index) in houses"
          :key="index"
          v-bind="houses"
          @click="viewDetail"
        >
          <b-td>
            <b-row style="width: 100%">
              <b-col cols="4">
                <b-img
                  src="https://picsum.photos/125/125/?image=58"
                  v-bind="mainProps"
                  rounded="0"
                  alt="Not rounded image"
                ></b-img
              ></b-col>
              <b-col cols="8">
                <div>매매 3억 9,000</div>
                <div>25m^2 3층</div>
              </b-col>
            </b-row>
          </b-td>
        </b-tr>
      </tbody>
    </b-table-simple>
  </div>
</template>

<script>
export default {
  data() {
    return {
      houses: [{ house: 1 }, { house: 2 }],
    };
  },
  methods: {
    viewDetail() {
      this.$router.push({
        name: "HouseDetail",
        params: { no: 1 },
      });
    },
  },
};
</script>

<style></style>
