package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.AptListRequest;
import com.ssafy.happyhouse.model.dto.housemap.CompareRequest;
import com.ssafy.happyhouse.model.dto.housemap.CompareResponse;
import com.ssafy.happyhouse.model.dto.housemap.DealResponse;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class HouseMapController {
	
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
	public CompareResponse compareApt(CompareRequest req) {
		return houseMapService.getCompareInfo(req);
	}
}