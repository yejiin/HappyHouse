package com.ssafy.happyhouse.model.dto.housemap;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HouseListRequest {

	private String gugunCode;
	private String dong;
	private int page;

}
