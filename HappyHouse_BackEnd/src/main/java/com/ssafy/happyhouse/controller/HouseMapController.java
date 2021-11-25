package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.AptListRequest;
import com.ssafy.happyhouse.model.dto.housemap.ChartResponse;
import com.ssafy.happyhouse.model.dto.housemap.CompareRequest;
import com.ssafy.happyhouse.model.dto.housemap.CompareResponse;
import com.ssafy.happyhouse.model.dto.housemap.DealResponse;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.FavoriteDto;
import com.ssafy.happyhouse.model.dto.housemap.FavoriteRequest;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseMapController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final HouseMapService houseMapService;
	
	@GetMapping("/sido")
	public List<SidoDto> sido() throws Exception {
		return houseMapService.getSido();
	}
	
	@GetMapping("/gugun")
	public List<GugunDto> gugun(@RequestParam("sido") String sido) throws Exception {
		return houseMapService.getGugunInSido(sido);
	}
	
	@GetMapping("/dong")
	public List<DongDto> dong(@RequestParam("gugun") String gugun) throws Exception {
		return houseMapService.getDongInGugun(gugun);
	}
	
	@GetMapping("/apt")
	public List<AptInfoDto> apt(AptListRequest req) throws Exception {
		log.info("apt",req);
		return houseMapService.getAptInDong(req);
	}
	
	@GetMapping("/apt/deal")
	public DealResponse aptDeal(@RequestParam("dong") String dong, @RequestParam("jibun") String jibun) throws Exception {
		log.info(dong + " " + jibun);
		return houseMapService.getAptDeal(dong, jibun);
	}
	
	@GetMapping("/apt/favorite/{userid}")
	public List<AptInfoDto> favoriteApt(@PathVariable("userid") String userid) throws Exception {
		return houseMapService.getFavoriteApt(userid);
	}
	
	@GetMapping("/apt/compare")
	public CompareResponse compareApt(CompareRequest req) throws Exception {
		return houseMapService.getCompareInfo(req);
	}
	
	@GetMapping("/{name}/{userid}")
	public int favorite(@PathVariable("name") String name, @PathVariable("userid") String userid) throws Exception {
		log.info("관심 여부 조회 : " + name + ", " + userid + "," +houseMapService.favorite(name, userid));
		return houseMapService.favorite(name, userid);
	}
	
	@PostMapping("/{name}/{userid}")
	public ResponseEntity<String> addfavorite(@PathVariable("name") String name, @PathVariable("userid") String userid, @RequestBody FavoriteRequest body) throws Exception {
		if(houseMapService.addfavorite(name, userid, body.getGugunname(), body.getDong(), body.getJibun())){
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@DeleteMapping("/{name}/{userid}")
	public ResponseEntity<String> cancelFavorite(@PathVariable("name") String name, @PathVariable("userid") String userid) throws Exception {
		if (houseMapService.cancelfavorite(name, userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/list/{gugunname}/{name}/{userid}")
	public List<FavoriteDto> favoriteInGugun(@PathVariable("gugunname") String gugunname,@PathVariable("name") String name, @PathVariable("userid") String userid) throws Exception {
		log.info("구군 관심 지역 조회");
		return houseMapService.getFavoriteInGugun(gugunname, name, userid);
	}
	
	@GetMapping("/chart")
	public ChartResponse chart(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("name") String name) throws Exception {
		
		return houseMapService.getChartData(lat, lng, name);
	}
}
