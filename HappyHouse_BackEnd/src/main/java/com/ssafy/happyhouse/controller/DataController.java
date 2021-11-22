package com.ssafy.happyhouse.controller;

import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;
import com.ssafy.happyhouse.model.service.DataService;
import com.ssafy.happyhouse.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Api(value="house", description="Happy house")
public class DataController {

   private static final Logger logger = LoggerFactory.getLogger(DataController.class);
      
   private final DataService dataService;
   private final HouseMapService houseMapService;
   
   /* 오피스텔 전월세 정보 */
   @ApiOperation(value = "오피스텔 전월세 정보를 DB에 저장")
   @PostMapping("/addOfficeRent/{gunguCode}/{dealYmd}")
   public void addOfficeRentData(@PathVariable String gunguCode, @PathVariable String dealYmd) throws Exception {

      List<GugunDto> gugun = houseMapService.getGugunInSido("11");
      try {
         for(int g=0; g<gugun.size(); g++) {
            for(int m=0; m<=10; m++) {
               String urlstr = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiRent?" +
                     "LAWD_CD=" + gugun.get(g).getGugunCode() +
                     "&DEAL_YMD=" + String.valueOf(Integer.valueOf(dealYmd)+m) +
                       "&serviceKey=Bw9%2ByHd1WfR0B0YZqdBLgAQ9gl7qE%2FbeBNo7n4Vwb6fSV25f3glKTxXmVPW5X9DtHYvmiRjG3s6Rr6gPm3os6Q%3D%3D";
                     URL url = new URL(urlstr);

                     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                     Document doc = dBuilder.parse(urlstr);
                     // root tag
                     doc.getDocumentElement().normalize();

                     NodeList nList = doc.getElementsByTagName("item");
                     
                     for(int i = 0; i<nList.getLength();i++) {
                        Node nNode = nList.item(i);
                        if(nNode.getNodeType() == Node.ELEMENT_NODE){
                                       
                           Element eElement = (Element) nNode;
                           
                           DealDto dealdto = new DealDto();
                           dealdto.setArea(getTagValue("전용면적",eElement).trim());
                           dealdto.setDealDay(getTagValue("일",eElement).trim());
                           dealdto.setDealMonth(getTagValue("월",eElement).trim());
                           dealdto.setDealYear(getTagValue("년",eElement).trim());
                           dealdto.setDeposit(getTagValue("보증금",eElement).trim());
                           dealdto.setRentMoney(getTagValue("월세",eElement).trim());
                           dealdto.setFloor(getTagValue("층",eElement).trim());
                           dealdto.setDong(getTagValue("법정동",eElement).trim());
                           dealdto.setGugunCode(getTagValue("지역코드",eElement).trim());
                           dealdto.setBuildYear(getTagValue("건축년도",eElement).trim());
                           dealdto.setName(getTagValue("단지",eElement).trim());
                           dealdto.setJibun(getTagValue("지번",eElement).trim());
                           logger.info("deal: ", dealdto);
                           dataService.addOfficeRentData(dealdto);
                           
                        }
                           
                     }
            }
         }
         
      }catch(Exception e){
         e.printStackTrace();
      }
   }
   
   /* 오피스텔 매매 정보 */
   @ApiOperation(value = "오피스텔 매매 정보를 DB에 저장")
   @PostMapping("/addOfficeDeal/{gunguCode}/{dealYmd}")
   public void addOfficeDealData(@PathVariable String gunguCode, @PathVariable String dealYmd) throws Exception {
      List<GugunDto> gugun = houseMapService.getGugunInSido("11");
      try {
         for(int g=0; g<gugun.size(); g++) {
            for(int m=0; m<=10; m++) {
               String urlstr = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiTrade?" +
                     "LAWD_CD=" + gugun.get(g).getGugunCode() +
                     "&DEAL_YMD=" + String.valueOf(Integer.valueOf(dealYmd)+m) +
                       "&serviceKey=Bw9%2ByHd1WfR0B0YZqdBLgAQ9gl7qE%2FbeBNo7n4Vwb6fSV25f3glKTxXmVPW5X9DtHYvmiRjG3s6Rr6gPm3os6Q%3D%3D";
                     URL url = new URL(urlstr);
                     
                     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                     Document doc = dBuilder.parse(urlstr);
                     // root tag
                     doc.getDocumentElement().normalize();
                     
                     NodeList nList = doc.getElementsByTagName("item");
                     
                     for(int i = 0; i<nList.getLength();i++) {
                        Node nNode = nList.item(i);
                        if(nNode.getNodeType() == Node.ELEMENT_NODE){
                                       
                           Element eElement = (Element) nNode;
                           
                           DealDto dealdto = new DealDto();
                           dealdto.setArea(getTagValue("전용면적",eElement).trim());
                           dealdto.setDealDay(getTagValue("일",eElement).trim());
                           dealdto.setDealMonth(getTagValue("월",eElement).trim());
                           dealdto.setDealYear(getTagValue("년",eElement).trim());
                           dealdto.setDealAmount(getTagValue("거래금액",eElement).trim());
                           dealdto.setFloor(getTagValue("층",eElement).trim());
                           dealdto.setDong(getTagValue("법정동",eElement).trim());
                           dealdto.setGugunCode(getTagValue("지역코드",eElement).trim());
                           dealdto.setBuildYear(getTagValue("건축년도",eElement).trim());
                           dealdto.setName(getTagValue("단지",eElement).trim());
                           dealdto.setJibun(getTagValue("지번",eElement).trim());
                           
                           dataService.addOfficeDealData(dealdto);
                           
                        }
                           
                     }
            }
         }
         
      }catch(Exception e){
         e.printStackTrace();
      }
   }
      
   /* 연립 다세대 전월세 정보 */
   @ApiOperation(value = "연립 다세대 전월세 정보를 DB에 저장")
   @PostMapping("/addHomeRent/{gunguCode}/{dealYmd}")
   public void addHomeRentData(@PathVariable String gunguCode, @PathVariable String dealYmd) throws Exception {
      List<GugunDto> gugun = houseMapService.getGugunInSido("11");
      try {
         for(int g=0; g<gugun.size(); g++) {
            for(int m=0; m<=10; m++) {
               String urlstr = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent?" +
                     "LAWD_CD=" + gugun.get(g).getGugunCode() +
                     "&DEAL_YMD=" + String.valueOf(Integer.valueOf(dealYmd)+m) +
                 "&serviceKey=Bw9%2ByHd1WfR0B0YZqdBLgAQ9gl7qE%2FbeBNo7n4Vwb6fSV25f3glKTxXmVPW5X9DtHYvmiRjG3s6Rr6gPm3os6Q%3D%3D";
               URL url = new URL(urlstr);
               
               DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
               DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
               Document doc = dBuilder.parse(urlstr);
               // root tag
               doc.getDocumentElement().normalize();
               
               NodeList nList = doc.getElementsByTagName("item");
               
               for(int i = 0; i<nList.getLength();i++) {
                  Node nNode = nList.item(i);
                  if(nNode.getNodeType() == Node.ELEMENT_NODE){
                                 
                     Element eElement = (Element) nNode;
                     
                     DealDto dealdto = new DealDto();
                     
                     logger.info(getTagValue("전용면적",eElement).trim());
                     dealdto.setArea(getTagValue("전용면적",eElement).trim());
                     dealdto.setDealDay(getTagValue("일",eElement).trim());
                     dealdto.setDealMonth(getTagValue("월",eElement).trim());
                     dealdto.setDealYear(getTagValue("년",eElement).trim());
                     dealdto.setDeposit(getTagValue("보증금액",eElement).trim());
                     dealdto.setRentMoney(getTagValue("월세금액",eElement).trim());
                     dealdto.setFloor(getTagValue("층",eElement).trim());
                     dealdto.setDong(getTagValue("법정동",eElement).trim());
                     dealdto.setGugunCode(getTagValue("지역코드",eElement).trim());
                     dealdto.setBuildYear(getTagValue("건축년도",eElement).trim());
                     dealdto.setName(getTagValue("연립다세대",eElement).trim());
                     dealdto.setJibun(getTagValue("지번",eElement).trim());
                     
                     dataService.addHomeRentData(dealdto);
                     
                  }
                     
               }
            }
            
         }
         
      }catch(Exception e){
         e.printStackTrace();
      }
   }
   
   /* 연립 다세대 매매 정보 */
   @ApiOperation(value = "연립 다세대 매매 정보를 DB에 저장")
   @PostMapping("/addHomeDeal/{gunguCode}/{dealYmd}")
   public void addHomeDealData(@PathVariable String gunguCode, @PathVariable String dealYmd) throws Exception {
      List<GugunDto> gugun = houseMapService.getGugunInSido("11");
      System.out.println(gugun.size());
      try {
         for(int g=0; g<gugun.size(); g++) {
            for(int m=0; m<=10; m++) {
            	String urlstr = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade?" +
                     "LAWD_CD=" + gugun.get(g).getGugunCode() +
                     "&DEAL_YMD=" + String.valueOf(Integer.valueOf(dealYmd)+m) +
                       "&serviceKey=Bw9%2ByHd1WfR0B0YZqdBLgAQ9gl7qE%2FbeBNo7n4Vwb6fSV25f3glKTxXmVPW5X9DtHYvmiRjG3s6Rr6gPm3os6Q%3D%3D";
                    
               		
               		URL url = new URL(urlstr);
                     
                     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                     Document doc = dBuilder.parse(urlstr);
                     // root tag
                     doc.getDocumentElement().normalize();
                     
                     NodeList nList = doc.getElementsByTagName("item");
                     
                     for(int i = 0; i<nList.getLength();i++) {
                    	logger.info("i: " + i);
                        Node nNode = nList.item(i);
                        if(nNode.getNodeType() == Node.ELEMENT_NODE){
                                       
                           Element eElement = (Element) nNode;
                           
                           DealDto dealdto = new DealDto();
                           dealdto.setArea(getTagValue("전용면적",eElement).trim());
                           dealdto.setDealDay(getTagValue("일",eElement).trim());
                           dealdto.setDealMonth(getTagValue("월",eElement).trim());
                           dealdto.setDealYear(getTagValue("년",eElement).trim());
                           dealdto.setDealAmount(getTagValue("거래금액",eElement).trim());
                           dealdto.setFloor(getTagValue("층",eElement).trim());
                           dealdto.setDong(getTagValue("법정동",eElement).trim());
                           dealdto.setGugunCode(getTagValue("지역코드",eElement).trim());
                           dealdto.setBuildYear(getTagValue("건축년도",eElement).trim());
                           dealdto.setName(getTagValue("연립다세대",eElement).trim());
                           dealdto.setJibun(getTagValue("지번",eElement).trim());
                           dataService.addHomeDealData(dealdto);
                           
                        }
                           
                     }
            }
            
         }
         
         
      }catch(Exception e){
         e.printStackTrace();
      }
   }
      
      /* 아파트 전월세 정보 */
      @ApiOperation(value = "아파트 전월세 정보를 DB에 저장")
      @PostMapping("/addAptRent/{gunguCode}/{dealYmd}")
      public void addAptRentData(@PathVariable String gunguCode, @PathVariable String dealYmd) throws Exception {
         List<GugunDto> gugun = houseMapService.getGugunInSido("11");
         try {
            for(int g=0; g<gugun.size(); g++) {
               for(int m=0; m<=10; m++) {
                  String urlstr = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?" +
                        "LAWD_CD=" + gugun.get(g).getGugunCode() +
                        "&DEAL_YMD=" + String.valueOf(Integer.valueOf(dealYmd)+m) +
                          "&serviceKey=Bw9%2ByHd1WfR0B0YZqdBLgAQ9gl7qE%2FbeBNo7n4Vwb6fSV25f3glKTxXmVPW5X9DtHYvmiRjG3s6Rr6gPm3os6Q%3D%3D";
                        URL url = new URL(urlstr);
                        
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(urlstr);
                        // root tag
                        doc.getDocumentElement().normalize();
                        
                        NodeList nList = doc.getElementsByTagName("item");
                        
                        for(int i = 0; i<nList.getLength();i++) {
                           Node nNode = nList.item(i);
                           if(nNode.getNodeType() == Node.ELEMENT_NODE){
                                          
                              Element eElement = (Element) nNode;
                              
                              DealDto dealdto = new DealDto();
                              dealdto.setArea(getTagValue("전용면적",eElement).trim());
                              dealdto.setDealDay(getTagValue("일",eElement).trim());
                              dealdto.setDealMonth(getTagValue("월",eElement).trim());
                              dealdto.setDealYear(getTagValue("년",eElement).trim());
                              dealdto.setDeposit(getTagValue("보증금액",eElement).trim());
                              dealdto.setRentMoney(getTagValue("월세금액",eElement).trim());
                              dealdto.setFloor(getTagValue("층",eElement).trim());
                              dealdto.setDong(getTagValue("법정동",eElement).trim());
                              dealdto.setGugunCode(getTagValue("지역코드",eElement).trim());
                              dealdto.setBuildYear(getTagValue("건축년도",eElement).trim());
                              dealdto.setName(getTagValue("아파트",eElement).trim());
                              dealdto.setJibun(getTagValue("지번",eElement).trim());
                              
                              dataService.addAptRentData(dealdto);
                              
                           }
                              
                        }
               }
            }
            
         }catch(Exception e){
            e.printStackTrace();
         }
      }
      
      /* 아파트 매매 정보 */
      @ApiOperation(value = "아파트 매매 정보를 DB에 저장")
      @PostMapping("/addAptDeal/{gunguCode}/{dealYmd}")
      public void addHomeAptData(@PathVariable String gunguCode, @PathVariable String dealYmd) throws Exception {
         List<GugunDto> gugun = houseMapService.getGugunInSido("11");
         try {
            for(int g=0; g<gugun.size(); g++) {
               for(int m=0; m<=10; m++) {
                  String urlstr = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?" +
                        "LAWD_CD=" + gugun.get(g).getGugunCode() +
                        "&DEAL_YMD=" + String.valueOf(Integer.valueOf(dealYmd)+m) +
                          "&serviceKey=Bw9%2ByHd1WfR0B0YZqdBLgAQ9gl7qE%2FbeBNo7n4Vwb6fSV25f3glKTxXmVPW5X9DtHYvmiRjG3s6Rr6gPm3os6Q%3D%3D";
                        URL url = new URL(urlstr);
                        
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(urlstr);
                        // root tag
                        doc.getDocumentElement().normalize();
                        
                        NodeList nList = doc.getElementsByTagName("item");
                        
                        for(int i = 0; i<nList.getLength();i++) {
                           Node nNode = nList.item(i);
                           if(nNode.getNodeType() == Node.ELEMENT_NODE){
                                          
                              Element eElement = (Element) nNode;
                              
                              DealDto dealdto = new DealDto();
                              dealdto.setArea(getTagValue("전용면적",eElement).trim());
                              dealdto.setDealDay(getTagValue("일",eElement).trim());
                              dealdto.setDealMonth(getTagValue("월",eElement).trim());
                              dealdto.setDealYear(getTagValue("년",eElement).trim());
                              dealdto.setDealAmount(getTagValue("거래금액",eElement).trim());
                              dealdto.setFloor(getTagValue("층",eElement).trim());
                              dealdto.setDong(getTagValue("법정동",eElement).trim());
                              dealdto.setGugunCode(getTagValue("법정동시군구코드",eElement).trim());
                              dealdto.setBuildYear(getTagValue("건축년도",eElement).trim());
                              dealdto.setName(getTagValue("아파트",eElement).trim());
                              dealdto.setJibun(getTagValue("지번",eElement).trim());
            
                              dataService.addAptDealData(dealdto);
                              
                           }
                              
                        }
               }
            }
            
         }catch(Exception e){
            e.printStackTrace();
         }
      }

      private static String getTagValue(String tag, Element eElement) {
    	  logger.info("태그: " + tag);
          NodeList nlList=null;
          try {
             nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
          }catch(NullPointerException e) {
             System.out.println(e.getMessage());
             return "0";
          }
           
           Node nValue = (Node) nlList.item(0);
           if(nValue == null) 
               return null;
           return nValue.getNodeValue();
       }

}
