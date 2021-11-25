package com.ssafy.happyhouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.chart.ChartResponse;
import com.ssafy.happyhouse.model.service.ChartService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chart")
public class ChartController {
	
	private final ChartService chartService;
	
	@GetMapping("")
	public ChartResponse chart(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam("name") String name) {
		
		return chartService.getChartData(lat, lng, name);
	}

}
