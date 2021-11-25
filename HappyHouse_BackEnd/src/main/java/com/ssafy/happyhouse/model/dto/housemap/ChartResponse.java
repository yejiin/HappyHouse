package com.ssafy.happyhouse.model.dto.housemap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartResponse {
	
	private ChartDto curData;
	
	private ChartDto agvData;

}
