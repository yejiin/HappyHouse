package com.ssafy.happyhouse.model.dto.housemap;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CompareDto {
	
	private String address;
	private List<DealFormatDto> deal;
	private Add add;
	private Traffic traffic;
	private Conv conv;
	
	public CompareDto(AptDetailDto dto, AptCodeDto aptdto, List<DealDto> dealList) {
		this.address = aptdto.getAptAddress();
		this.deal = dealList.stream().map((deal) -> new DealFormatDto(deal)).collect(Collectors.toList());
		this.add = new Add(dto.getParkUCnt(), dto.getParkDCnt(), dto.getCctvCnt());
		this.traffic = new Traffic(dto.getDisBus(), dto.getSubLine(), dto.getSubStation(), dto.getDisSub());
		this.conv = new Conv(dto.getWelFac(), dto.getConvFac(), dto.getEduFac());
	}

}

@Data
@AllArgsConstructor
class Add {
	private String parkUCnt;
	private String parkDCnt;
	private String cctvCnt;
}

@Data
@AllArgsConstructor
class Traffic {
	private String disBus;
	private String subLine;
	private String subStation;
	private String disSub;
}

@Data
@AllArgsConstructor
class Conv {
	private String welFac;
	private String convFac;
	private String eduFac;	
}