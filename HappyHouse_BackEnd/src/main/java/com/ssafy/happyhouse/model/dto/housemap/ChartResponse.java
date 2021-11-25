package com.ssafy.happyhouse.model.dto.housemap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ChartResponse {
	
	private ChartDto curData;
	
	private ChartDto agvData;

}
