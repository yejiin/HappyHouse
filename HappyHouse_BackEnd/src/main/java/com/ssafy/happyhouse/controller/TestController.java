package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	
	@GetMapping("/test")
	public String test() {
		
		String[] cate = {"MT1", "CS2", "PS3"};
		
		String apiKey = "6a640507a314058d490bb6d379a72600";
		String apiUrl = "https://dapi.kakao.com/v2/local/search/category.json";
	    String jsonString = null;
	    
	    try {
	    	for (int i = 0; i <= 10; i++) {
			String param = URLEncoder.encode(cate[0], "UTF-8");
		    String x = "37.58936620000001";
		    String y = "126.9697192";
		    Integer radius = 100;
		    
		    String addr = apiUrl + "?category_group_code=" +param + "&x=" + x +"&y=" +y + "&radius=" + radius; 
		    
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
	        System.out.println(i);
	    	}
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return jsonString;
	    
	}

}
