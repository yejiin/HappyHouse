package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.housemap.HouseListRequest;
import com.ssafy.happyhouse.model.dto.housemap.HouseListResponse;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

import lombok.RequiredArgsConstructor;

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
	
	@GetMapping("/list")
	public HouseListResponse house(HouseListRequest req) throws Exception {
		System.out.println(req);
		return houseMapService.getHouseInDong(req);
	}
}