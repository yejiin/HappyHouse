package com.ssafy.happyhouse.model.dto.housemap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AptInfoDto {

	private String dong;
	private String gugunCode;
	private String buildYear;
	private String name;
	private String jibun;
	private String lat;
	private String lng;
	private String gugunName;
}
