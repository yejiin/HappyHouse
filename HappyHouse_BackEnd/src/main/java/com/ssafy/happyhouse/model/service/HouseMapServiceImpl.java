package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.dto.housemap.AptCodeDto;
import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.AptListRequest;
import com.ssafy.happyhouse.model.dto.housemap.ChartDto;
import com.ssafy.happyhouse.model.dto.housemap.ChartResponse;
import com.ssafy.happyhouse.model.dto.housemap.CompareDto;
import com.ssafy.happyhouse.model.dto.housemap.CompareRequest;
import com.ssafy.happyhouse.model.dto.housemap.CompareResponse;
import com.ssafy.happyhouse.model.dto.housemap.DealFormatDto;
import com.ssafy.happyhouse.model.dto.housemap.DealRangeDto;
import com.ssafy.happyhouse.model.dto.housemap.DealResponse;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.FavoriteDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	private final int RADIUS = 1000;
	
	private final SqlSession sqlSession;

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

	// 동에 따른 아파트 리스트 (페이징 처리 x)
	@Override
	public List<AptInfoDto> getAptInDong(AptListRequest req) throws Exception {
//		int numberOfPages = 10;
//		int page = req.getPage();
//		int start = numberOfPages * (page - 1);
				
		List<AptInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).getAptInDong(req.getGugunCode(), req.getDong());
		
		//아파트 주소를 좌표로 저장
		for (AptInfoDto dto : list) {
			String address = "서울시 " + dto.getGugunName() + " " + dto.getDong() + " " + dto.getJibun();
			String jsonString = getKakaoApiFromAddress(address);
			HashMap<String, String> XYMap = getXYMapFromJson(jsonString);
			dto.setLat(XYMap.get("y"));
			dto.setLng(XYMap.get("x"));
			
			if (dto.getAptCode() != null)
				dto.setComparable(true);
		}

		return list;
	}
	
	@Override
	public DealResponse getAptDeal(String dong, String jibun) throws Exception {
		
		DealRangeDto range = sqlSession.getMapper(HouseMapMapper.class).getDealRange(dong, jibun);
		if (range != null)
			range.setMaxAmount(formatMoney(range.getMaxAmount()));
		if (range != null)
			range.setMinAmount(formatMoney(range.getMinAmount()));
		
		List<DealDto> dealList = sqlSession.getMapper(HouseMapMapper.class).getAptDeal(dong, jibun);
		List<DealFormatDto> deals = dealList.stream().map((dto) -> new DealFormatDto(dto)).collect(Collectors.toList());

		return new DealResponse(range, deals);
	}
	
	@Override
	public List<AptInfoDto> getFavoriteApt(String userid) throws Exception {
		List<AptInfoDto> list = sqlSession.getMapper(HouseMapMapper.class).getFavoriteApt(userid);
		
		for (AptInfoDto dto : list) {
			String address = "서울시 " + dto.getGugunName() + " " + dto.getDong() + " " + dto.getJibun();
			log.debug("주소 : " + address);
			String jsonString = getKakaoApiFromAddress(address);
			HashMap<String, String> XYMap = getXYMapFromJson(jsonString);
			dto.setLat(XYMap.get("y"));
			dto.setLng(XYMap.get("x"));
		}

		return list;
	}
	
	@Override
	public CompareResponse getCompareInfo(CompareRequest req) throws SQLException {
		AptCodeDto aptCode = sqlSession.getMapper(HouseMapMapper.class).getAptCode(req.getName(), req.getDong());
		log.debug("아파트 코드" , aptCode);
		if (aptCode == null) { 
			log.debug("아파트 코드 is null" , aptCode);
		}
		AptDetailDto dto = sqlSession.getMapper(HouseMapMapper.class).getAptDetail(aptCode.getAptCode());
		List<DealDto> dealdto = sqlSession.getMapper(HouseMapMapper.class).getDealLatest(req.getDong(), req.getName());
		
		AptCodeDto compAptCode = sqlSession.getMapper(HouseMapMapper.class).getAptCode(req.getCompName(), req.getCompDong());
		AptDetailDto compdto = sqlSession.getMapper(HouseMapMapper.class).getAptDetail(compAptCode.getAptCode());
		List<DealDto> compDealdto = sqlSession.getMapper(HouseMapMapper.class).getDealLatest(req.getCompDong(), req.getCompName());
		
		return new CompareResponse(new CompareDto(dto, aptCode, dealdto), new CompareDto(compdto, compAptCode, compDealdto));
	}
	
	@Override
	public ChartResponse getChartData(String lat, String lng, String name) throws Exception {
		
		ChartDto dto = sqlSession.getMapper(HouseMapMapper.class).getChartData(name);
		ChartDto maxDto = sqlSession.getMapper(HouseMapMapper.class).getMaxData();
		ChartDto avgDto = sqlSession.getMapper(HouseMapMapper.class).getAvgData();

		if (dto == null) {
			dto = addChartData(lat, lng, name);
		}

		dto = calcRatio(dto, maxDto);
		avgDto = calcRatio(avgDto, maxDto);

		return new ChartResponse(dto, avgDto);
	}


	@Override
	public ChartDto addChartData(String lat, String lng, String name) throws Exception {
		
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
	    
	    sqlSession.getMapper(HouseMapMapper.class).addChartData(dto);
	    return dto;
	}
	
	@Override
	public int favorite(String name, String userid) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).favorite(name, userid);
	}

	@Override
	public boolean addfavorite(String name, String userid, String gugunname, String dong, String jibun) throws Exception {
	return sqlSession.getMapper(HouseMapMapper.class).addfavorite(name, userid, gugunname, dong, jibun) == 1;
}

	@Override
	public boolean cancelfavorite(String name, String userid) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).cancelfavorite(name, userid) == 1;
	}


	@Override
	public List<FavoriteDto> getFavoriteInGugun(String gugunname, String name, String userid) throws Exception {
		List<FavoriteDto> list = sqlSession.getMapper(HouseMapMapper.class).getFavoriteInGugun(gugunname, name, userid);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setId(i + 1);
			if (list.get(i).getAptCode() != null)
				list.get(i).setComparable(true);
		}
		System.out.println(list);
		
		return list;
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
	
	// 주소를 좌표로 전환
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
	
	// 좌표를 전환한 json string에서 좌표만 추출
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

	public ChartDto calcRatio(ChartDto cur, ChartDto max) throws Exception {
		
		cur.setConvenience(Math.round(cur.getConvenience() / max.getConvenience()*1000) / 1000.0);
		cur.setEducation(Math.round(cur.getEducation() / max.getEducation()*1000) / 1000.0);
		cur.setTraffic(Math.round(cur.getTraffic() / max.getTraffic()*1000) / 1000.0);
		cur.setPublicFac(Math.round(cur.getPublicFac() / max.getPublicFac()*1000) / 1000.0);
		cur.setMedical(Math.round(cur.getMedical() / max.getMedical()*1000) / 1000.0);
		return cur;
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
