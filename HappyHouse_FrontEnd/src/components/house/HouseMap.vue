<template>
  <div style="height: 100%">
    <div id="map" style="width: 100%; height: 100%"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
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
  computed: {
    ...mapState(houseStore, ["houses", "sido", "gugun"]),
  },
  watch: {
    houses: function () {
      this.displayMarkers();
    },
  },
  methods: {
    initMap() {
      console.log("init");
      const mapContainer = document.getElementById("map"); // 지도를 표시할 div
      const mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.displayMarkers();
    },

    displayMarkers() {
      const bounds = new kakao.maps.LatLngBounds();

      // 남아있던 마커 지우기
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      this.houses.mapInfo.forEach((house) => {
        const placePosition = new kakao.maps.LatLng(house.lat, house.lng);
        console.log(house);
        bounds.extend(placePosition);
        this.map.setBounds(bounds);

        let marker = new kakao.maps.Marker({
          map: this.map,
          position: placePosition,
        });
        this.markers.push(marker);
        this.addOverlay(house, marker, placePosition);
      });
    },

    addOverlay(house, marker, position) {
      var content = `<div class="wrap">
            <div class="info">
              <div class="title">${house.name}</div>
              <div class="body">
                <div class="desc">
                  <div class="ellipsis">${house.address}</div>
                  <div class="ellipsis">건축년도: ${house.buildYear}</div>
                </div>
              </div>
            </div>
          </div>`;

      var overlay = new kakao.maps.CustomOverlay({
        content: content,
        position: position,
      });

      kakao.maps.event.addListener(
        marker,
        "mouseover",
        this.makeOverListener(this.map, overlay)
      );
      kakao.maps.event.addListener(
        marker,
        "mouseout",
        this.makeOutListener(overlay)
      );
    },

    makeOverListener(map, overlay) {
      return function () {
        overlay.setMap(map);
      };
    },

    makeOutListener(overlay) {
      return function () {
        overlay.setMap(null);
      };
    },
  },
};
</script>

<style>
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 13px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}

.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
