package com.ssafy.happyhouse.model.dto.housemap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AptInfoDto {

	private String dong;
	private String gugunCode;
	private String buildYear;
	private String name;
	private String jibun;
	private String lat;
	private String lng;
	private String gugunName;
	private String aptCode;
	private boolean isComparable;
}
