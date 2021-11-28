<template>
  <div class="map_wrap" style="height: 100vh">
    <div id="map" style="width: 100%; height: 100vh"></div>
    <ul id="category">
      <li id="BK9" data-order="0" @click="onClickCategory($event)">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1" @click="onClickCategory($event)">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2" @click="onClickCategory($event)">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3" @click="onClickCategory($event)">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4" @click="onClickCategory($event)">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5" @click="onClickCategory($event)">
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";
const chartStore = "chartStore";
const favoriteStore = "favoriteStore";
const memberStore = "memberStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null, // 지도 객체
      markers: [],
      marker: null,

      placeOverlay: null, // 오버레이객체
      contentNode: document.createElement("div"),
      currCategory: null,
      ps: null, // 지도 검색 객체
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
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=d3586605b6e80b00c4ba672b8b64327a&libraries=services";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["houses", "house", "sido", "gugun"]),
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  watch: {
    houses: function () {
      if (this.houses.length === 0) {
        if (this.markers.length > 0) {
          this.markers.forEach((marker) => marker.setMap(null));
        }
      }
      this.displayMarkers();
    },
    house: function () {
      this.removeMarker();
      this.displayMarker(this.house);
    },
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "getDealList"]),
    ...mapActions(chartStore, ["getChartData"]),
    ...mapActions(favoriteStore, ["getFavoriteInGugun"]),

    initMap() {
      const mapContainer = document.getElementById("map"); // 지도를 표시할 div
      const mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.ps = new kakao.maps.services.Places(this.map);

      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);

      this.contentNode = document.createElement("div");
      this.contentNode.className = "placeinfo_wrap";
      this.addEventHandle(this.contentNode, "mousedown", kakao.maps.event.preventMap);
      this.addEventHandle(this.contentNode, "touchstart", kakao.maps.event.preventMap);
      this.placeOverlay.setContent(this.contentNode);

      this.displayMarkers();
    },

    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },

    // 카테고리 검색을 요청하는 함수입니다
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      this.ps.categorySearch(this.currCategory, this.placesSearchCB(), { useMapBounds: true });
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB() {
      let self = this;
      return function (data, status) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          self.displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      };
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      let self = this;
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      var order = document.getElementById(this.currCategory).getAttribute("data-order");

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        ((marker, place) => {
          kakao.maps.event.addListener(marker, "click", () => {
            self.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
      }

      content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(event) {
      var id = event.currentTarget.id;
      let className = event.currentTarget.className;
      console.log(id, event.currentTarget.className);
      this.placeOverlay.setMap(null);

      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(event);
        this.searchPlaces();
      }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      var category = document.getElementById("category");
      let children = category.children;
      let i;

      for (i = 0; i < children.length; i++) {
        children[i].className = "";
      }

      if (el) {
        el.currentTarget.className = "on";
      }
    },

    displayMarker(house) {
      this.removeMarker();

      if (this.marker != null) {
        this.marker.setMap(null);
      }

      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      console.log("marker2", this.markers);
      const bounds = new kakao.maps.LatLngBounds();
      const placePosition = new kakao.maps.LatLng(house.lat, house.lng);
      bounds.extend(placePosition);
      this.map.setBounds(bounds);

      const imageSrc = require("@/assets/marker_map.png"), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(45, 45), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 45) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

      this.marker = new kakao.maps.Marker({
        map: this.map,
        position: placePosition,
        image: markerImage,
      });

      this.addOverlay(house, this.marker, placePosition, this.userInfo);

      this.marker.setMap(this.map);
    },

    displayMarkers() {
      const bounds = new kakao.maps.LatLngBounds();

      // 남아있던 마커 지우기
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      this.houses.forEach((house) => {
        const placePosition = new kakao.maps.LatLng(house.lat, house.lng);
        bounds.extend(placePosition);
        this.map.setBounds(bounds);
        let marker = new kakao.maps.Marker({
          map: this.map,
          position: placePosition,
        });
        this.markers.push(marker);
        this.addOverlay(house, marker, placePosition, this.userInfo);
      });
    },

    addOverlay(house, marker, position, user) {
      const content = `<div class="wrap">
            <div class="info">
              <div class="title">${house.name}</div>
              <div class="body">
                <div class="desc">
                  <div class="jibun"><i>클릭하여 상세정보를 확인하세요</i></div>
                </div>
              </div>
            </div>
          </div>`;

      const overlay = new kakao.maps.CustomOverlay({
        content: content,
        position: position,
      });

      kakao.maps.event.addListener(marker, "mouseover", this.makeOverListener(this.map, overlay));
      kakao.maps.event.addListener(marker, "mouseout", this.makeOutListener(overlay));
      kakao.maps.event.addListener(marker, "click", this.makeClickListener(house, overlay, user));
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

    makeClickListener(house, overlay, user) {
      let self = this;
      return function () {
        overlay.setMap(null);

        self.detailHouse(house);
        self.getDealList({ dong: house.dong, jibun: house.jibun });
        self.getChartData({ lat: house.lat, lng: house.lng, name: house.name });
        console.log("구군 관심지역 조회시 user정보", user);
        if (user) {
          console.log("구군 관심지역 조회");
          self.getFavoriteInGugun({
            gugunname: house.gugunName,
            housename: house.name,
            userid: user.userid,
          });
        }
        self.$router.replace({
          name: "HouseDetail",
          // query: { dong: house.dong, jibun: house.jibun },
        });
      };
    },
  },
};
</script>

<style>
.wrap {
  position: absolute;
  /* right: 40px; */
  /* left: 1px; */
  bottom: 40px;
  width: 60000px;
  height: 80px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrapa * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 200px;
  height: 80px;
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

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px
    center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
