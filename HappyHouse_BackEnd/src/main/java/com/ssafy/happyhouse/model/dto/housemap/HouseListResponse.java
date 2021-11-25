package com.ssafy.happyhouse.model.dto.housemap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class HouseListResponse {

	public int totalCount;
	public int page;
	public List<AptInfoDto> data;
	public List<MapDto> mapInfo;
}
