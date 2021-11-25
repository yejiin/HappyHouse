package com.ssafy.happyhouse.model.dto.housemap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class DealResponse {
	
	public DealRangeDto range;
	public List<DealFormatDto> deals;
}
