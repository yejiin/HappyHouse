package com.ssafy.happyhouse.model.dto.housemap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class MapDto {
	
	public String name;
	public String address;
	public String lat;
	public String lng;
	public String buildYear;

}
