package com.ssafy.happyhouse.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.AptListRequest;
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

@RequiredArgsConstructor
@Service
public class HouseMapServiceImpl implements HouseMapService {
	
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
		
		System.out.println(range);
		System.out.println(deals);
		return new DealResponse(range, deals);
	}
	
	
<<<<<<< HEAD
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

=======
>>>>>>> d68ef2cc187a62c76ef6ff738c85cf23a732c008
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
	



}
