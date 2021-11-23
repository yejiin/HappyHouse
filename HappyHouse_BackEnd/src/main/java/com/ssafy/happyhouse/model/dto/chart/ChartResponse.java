package com.ssafy.happyhouse.model.dto.chart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartResponse {
	
	private ChartDto curData;
	
	private ChartDto agvData;

}
