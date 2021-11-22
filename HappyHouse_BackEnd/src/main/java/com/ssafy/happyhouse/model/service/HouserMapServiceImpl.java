package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.housemap.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.HouseListParamDto;
import com.ssafy.happyhouse.model.dto.housemap.HouseListRequest;
import com.ssafy.happyhouse.model.dto.housemap.HouseListResponse;
import com.ssafy.happyhouse.model.dto.housemap.MapDto;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;
import com.ssafy.happyhouse.util.AptImagePath;

@Service
public class HouserMapServiceImpl implements HouseMapService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<GugunDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<DongDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public HouseListResponse getHouseInDong(HouseListRequest req) throws Exception {

		int numberOfPages = 10;
		int page = req.getPage();
		int start = numberOfPages * (page - 1);
		
		HouseListParamDto dto = new HouseListParamDto(req.getGugunCode(), req.getDong(), start, start + numberOfPages);
				
		List<HouseInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).getHouseInDong(dto);
		
		HashMap<String, HouseInfoDto> map = new HashMap<String, HouseInfoDto>();

		for (HouseInfoDto houseInfoDto : list) {
//			houseInfoDto.setImage(AptImagePath.aptImagePath[0]);
			if (!map.containsKey(houseInfoDto.getJibun())) {
				
				map.put(houseInfoDto.getJibun(), houseInfoDto);
			}
		}
		
		List<MapDto> mapDto = new ArrayList<MapDto>();
		
		for (String key: map.keySet()) {
			HouseInfoDto houseInfoDto = map.get(key);
			String address = "서울시 " + houseInfoDto.getGugunName() + " " + houseInfoDto.getDong() + " " + houseInfoDto.getJibun();
			
			String jsonString = getKakaoApiFromAddress(address);
			
	        HashMap<String, String> XYMap = getXYMapFromJson(jsonString);
	        
	        mapDto.add(new MapDto(houseInfoDto.getName(), address, XYMap.get("y"), XYMap.get("x"), houseInfoDto.getBuildYear()));
		}
		

		int totalCount = sqlSession.getMapper(HouseMapMapper.class).getTotalCount(dto);
		

        
		return new HouseListResponse(totalCount, page, list, mapDto);
	}
	
	public String getKakaoApiFromAddress(String address) {
		String apiKey = "6a640507a314058d490bb6d379a72600";
		String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
	    String jsonString = null;
	    
	    try {
			address = URLEncoder.encode(address, "UTF-8");
			
			String addr = apiUrl + "?query=" + address;
			
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
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return jsonString;
	}
	
	public HashMap<String, String> getXYMapFromJson(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> XYMap = new HashMap<String, String>();

	    try {
	        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>(){};
	        Map<String, Object> jsonMap = mapper.readValue(jsonString, typeRef);

	        @SuppressWarnings("unchecked")
	        List<Map<String, String>> docList  =  (List<Map<String, String>>) jsonMap.get("documents");	

	        Map<String, String> adList = (Map<String, String>) docList.get(0);
	        XYMap.put("x",adList.get("x"));
	        XYMap.put("y", adList.get("y"));
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    return XYMap;
	}

}
