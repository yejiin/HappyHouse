<template>
  <b-tr>
    <b-td class="tdClass">{{ qno }}</b-td>
    <b-td class="tdSubject">
      <router-link :to="{ name: 'QnAView', params: { qno: qno } }">{{ subject }}</router-link>
    </b-td>
    <b-td class="tdClass">{{ isreply }}</b-td>
    <b-td class="tdClass">{{ userid }}</b-td>
    <b-td class="tdClass">{{ regtime }}</b-td>
  </b-tr>
</template>

<script>
// import moment from "moment";

export default {
  name: "QnAListRow",
  props: {
    qno: Number,
    userid: String,
    subject: String,
    regtime: String,
    isreply: Number,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regtime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 150px;
  text-align: left;
}
</style>
