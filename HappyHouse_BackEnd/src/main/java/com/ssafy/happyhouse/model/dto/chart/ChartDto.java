package com.ssafy.happyhouse.model.dto.chart;

import lombok.Data;

@Data
public class ChartDto {
	
	private String name;
	private String lat;
	private String lng;
	
	private double convenience;
	private double education;
	private double traffic;
	private double publicFac;
	private double medical;
	
	public ChartDto(String name, String lat, String lng) {
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}
	
	public void addCon(int count) {
		convenience += count;
	}
	
	public void addEdu(int count) {
		education += count;
	}
	
	public void addTra(int count) {
		traffic += count;
	}
	
	public void addPub(int count) {
		publicFac += count;
	}
	
	public void addMed(int count) {
		medical += count;
	}

}
