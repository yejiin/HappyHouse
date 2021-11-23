package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.model.dto.chart.ChartDto;
import com.ssafy.happyhouse.model.dto.chart.ChartResponse;
import com.ssafy.happyhouse.model.mapper.ChartMapper;

import lombok.RequiredArgsConstructor;

/**
 * 편의  - MT1: 대형마트 / CS2: 편의점
 * 교육  - PS3: 유치원 / SC4: 학교
 * 교통 - PK6: 주차장 / SW8: 지하철역
 * 공공 - PO3: 곻공기관
 * 의료 - HP8: 병원 / PM9: 약국
 */
@RequiredArgsConstructor
@Service
public class ChartService {
	
	private final int RADIUS = 1000;
	
	private final SqlSession sqlSession;
	

	public ChartResponse getChartData(String lat, String lng, String name) {
		
		ChartDto dto = sqlSession.getMapper(ChartMapper.class).getChartData(name);
		ChartDto maxDto = sqlSession.getMapper(ChartMapper.class).getMaxData();
		ChartDto avgDto = sqlSession.getMapper(ChartMapper.class).getAvgData();

		if (dto == null) {
			dto = addChartData(lat, lng, name);
		}

		dto = calcRatio(dto, maxDto);
		avgDto = calcRatio(avgDto, maxDto);

		return new ChartResponse(dto, avgDto);
	}
	
	public ChartDto calcRatio(ChartDto cur, ChartDto max) {
		cur.setConvenience(Math.round(cur.getConvenience() / max.getConvenience()*1000) / 1000.0);
		cur.setEducation(Math.round(cur.getEducation() / max.getEducation()*1000) / 1000.0);
		cur.setTraffic(Math.round(cur.getTraffic() / max.getTraffic()*1000) / 1000.0);
		cur.setPublicFac(Math.round(cur.getPublicFac() / max.getPublicFac()*1000) / 1000.0);
		cur.setMedical(Math.round(cur.getMedical() / max.getMedical()*1000) / 1000.0);
		return cur;
	}
	
	
	public ChartDto addChartData(String lat, String lng, String name) {
		
		String[] cateCode = {"MT1", "CS2", "PS3", "SC4", "PK6", "SW8", "PO3", "HP8", "PM9"};
		
		String apiKey = "6a640507a314058d490bb6d379a72600";
		String apiUrl = "https://dapi.kakao.com/v2/local/search/category.json";
		
	    String jsonString = null;
	    
	    ChartDto dto = new ChartDto(name, lat, lng);
	    
	    try {
	    	for (int i = 0; i < cateCode.length; i++) {

	    	    
				String cate = URLEncoder.encode(cateCode[i], "UTF-8");
				
			    String addr = apiUrl + "?category_group_code=" + cate + "&x=" + lng +"&y=" + lat + "&radius=" + RADIUS; 
			    
			    URL url = new URL(addr);
			    URLConnection conn = url.openConnection();
			    conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
			    
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		        StringBuffer sb = new StringBuffer();
	
		        String line;
	
		        while ((line=br.readLine()) != null) {
		            sb.append(line);
		        }
	
		        jsonString = sb.toString();
		        br.close();
		        int totalCnt = getTotalCntFromJson(jsonString);
		        
		        if (i == 0 || i == 1) {
		        	dto.addCon(totalCnt);
		        } else if (i == 2 || i == 3) {
		        	dto.addEdu(totalCnt);
		        } else if (i == 4 || i == 5) {
		        	dto.addTra(totalCnt);
		        } else if (i == 6) {
		        	dto.addPub(totalCnt);
		        } else {
		        	dto.addMed(totalCnt);
		        }
		        
		    
	    	}
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    sqlSession.getMapper(ChartMapper.class).addChartData(dto);
	    return dto;
	}
	
	// 카테고리의 장소 리스트 데이터 중 총 개수만 추출
	public int getTotalCntFromJson(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		
        try {
        	 TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>(){};
			Map<String, Object> jsonMap = mapper.readValue(jsonString, typeRef);
		
	        @SuppressWarnings("unchecked")
	        Map<String, String> meta = (Map<String, String>) jsonMap.get("meta");	
	        
	        int totalCnt = Integer.parseInt(String.valueOf(meta.get("total_count")));
	        return totalCnt;
        } catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
	}
}
