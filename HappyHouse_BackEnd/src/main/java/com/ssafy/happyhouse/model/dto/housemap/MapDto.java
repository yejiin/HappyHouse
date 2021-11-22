package com.ssafy.happyhouse.model.dto.housemap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MapDto {
	
	public String name;
	public String address;
	public String lat;
	public String lng;
	public String buildYear;

}
