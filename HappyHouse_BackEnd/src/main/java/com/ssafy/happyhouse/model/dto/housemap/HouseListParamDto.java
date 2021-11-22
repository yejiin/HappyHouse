package com.ssafy.happyhouse.model.dto.housemap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseListParamDto {

	private String gugunCode;
	private String dong;
	private int start;
	private int end;

}
