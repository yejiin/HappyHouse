package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.chart.ChartDto;

public interface ChartMapper {

	ChartDto getChartData(String name);
	int addChartData(ChartDto dto);
	ChartDto getMaxData();
	ChartDto getAvgData();
}
