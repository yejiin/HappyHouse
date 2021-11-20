<template>
  <div style="height: 100%">
    <div id="map" style="width: 100%; height: 100%"></div>
  </div>
</template>

<script>
export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d3586605b6e80b00c4ba672b8b64327a";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
    },
  },
};
</script>

<style></style>
