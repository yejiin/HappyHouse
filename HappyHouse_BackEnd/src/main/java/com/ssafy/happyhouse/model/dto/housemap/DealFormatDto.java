package com.ssafy.happyhouse.model.dto.housemap;



import com.ssafy.happyhouse.model.dto.data.DealDto;

import lombok.Data;

@Data
public class DealFormatDto {
	
	private String area;
	private String date;
	private String price;
//	private String deposit;
//	private String rentMoney;
//	private String dealAmount;
	private String floor;
	private String dong;
	private String gugunCode;
	private String buildYear;
	private String name;
	private String jibun;
	
	public DealFormatDto(DealDto dto) {
		this.area = dto.getArea() + "㎡";
		this.date = dto.getDealYear() +"." + dto.getDealMonth() + "." + dto.getDealDay();
		this.price = formatDealPrice(dto);
//		this.deposit = formatMoney(dto.getDeposit());
//		this.rentMoney = formatMoney(dto.getRentMoney());
//		this.dealAmount = formatMoney(dto.getDealAmount());
		this.floor = dto.getFloor();
		this.dong = dto.getDong();
		this.gugunCode = dto.getGugunCode();
		this.buildYear = dto.getBuildYear();
		this.name = dto.getName();
		this.jibun = dto.getJibun();
	}
	
	private String formatDealPrice(DealDto dto) {
		
		if (dto.getDealAmount() != null) {
			return "매매 " + formatMoney(dto.getDealAmount());
		} else if (!dto.getRentMoney().equals("0")) {
			return "월세 " + formatMoney(dto.getDeposit()) + "/" + formatMoney(dto.getRentMoney());
		} else {
			return "전세 " + formatMoney(dto.getDeposit());
		}
	}
	
	private String formatMoney(String money) {
		if (money == null)
			return null;
		
		String result = "";
		int len = money.length();
		
		if (len > 5) {
			result += money.substring(0, len - 5) + "억";
			String s = money.substring(len - 5, len);	
			
			if (s.charAt(0) != '0')
				result += " " +s;
	
		} else {
			result += money;
		}
		
		return result;
	}
}
