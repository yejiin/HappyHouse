package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@Controller
@RequestMapping("/map")
public class HouseMapController {
	
	@Autowired
	private HouseMapService happyHouseMapService;
	
	@GetMapping()
	public String map() {
		return "viewAPT";
	}
	
	@GetMapping("/sido")
	@ResponseBody
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	@ResponseBody
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	@ResponseBody
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	@ResponseBody
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@GetMapping("/deal")
	@ResponseBody
	public ResponseEntity<List<HouseDealDto>> deal(@RequestParam("aptcode") String aptCode) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(happyHouseMapService.getDealByApt(aptCode), HttpStatus.OK);
	}
}