package com.ssafy.happyhouse.model.dto.housemap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompareResponse {
	
	public CompareDto cur;
	public CompareDto comp;
}
