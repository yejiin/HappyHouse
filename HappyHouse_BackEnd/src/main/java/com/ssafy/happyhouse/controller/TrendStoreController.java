package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.trendstore.TrendStoreDto;
import com.ssafy.happyhouse.model.service.UserService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/trendstore")
public class TrendStoreController {

	private static final Logger logger = LoggerFactory.getLogger(TrendStoreController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{ageGroup}")
	public ResponseEntity<TrendStoreDto> getStoreData(@PathVariable("ageGroup") int ageGroup) throws Exception {
		logger.debug("getStoreData - 호출");
		return new ResponseEntity<TrendStoreDto>(userService.getStoreData(ageGroup), HttpStatus.OK);
	}
	
//	@PostMapping("")
//	public ResponseEntity<List<TrendStoreDto>> registTstore(@RequestParam(value = "concerns[]") List<String> concerns) throws Exception {
//		logger.debug("registTstore - 호출");
//		userService.registTstore(concerns);
//		return new ResponseEntity<>(userService.listTstore(), HttpStatus.OK);
//	}
//	
//	@PostMapping("{favStore}")
//	public ResponseEntity<List<TrendStoreDto>> updateFavStoreCount(@PathVariable("favStore") String favStore) throws Exception {
//		logger.debug("updateFavStoreCount - 호출");
//		userService.updateCount(favStore);
//		return new ResponseEntity<>(userService.listTstore(), HttpStatus.OK);
//	}
	
}
