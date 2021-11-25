package com.ssafy.happyhouse.model.dto.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AptBasicDto {
	
	private String aptCode;
	private String aptName;
	private String aptAddr;
	private String dongCnt;
	private String aptTel;
	private String aptCnt;
	private String hoCnt;
	private String bjdCode;
}
