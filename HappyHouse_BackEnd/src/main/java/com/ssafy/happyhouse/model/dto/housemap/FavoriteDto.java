package com.ssafy.happyhouse.model.dto.housemap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteDto {

	private int id;
	private String name;
	private String gugunName;
	private String dong;
	private String jibun;
	private String aptCode;
	private boolean isComparable;
}
