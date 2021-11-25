package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParameterDto;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/qna")
@Api("QnA 컨트롤러  API")
public class QnAController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnAService qnaService;
	
	@ApiOperation(value = "문의 글작성", notes = "새로운 문의글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerQuestion(@RequestBody @ApiParam(value = "문의글 정보.", required = true) QuestionDto questionDto) throws Exception {
		logger.info("registerQuestion - 호출");
		if (qnaService.registerQuestion(questionDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "문의 글목록", notes = "모든 문의글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QuestionDto>> listQuestion(@ApiParam(value = "문의글을 얻기위한 부가정보.", required = true) QuestionParameterDto questionParameterDto) throws Exception {
		logger.info("listQuestion - 호출");
		return new ResponseEntity<List<QuestionDto>>(qnaService.listQuestion(questionParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "문의 글보기", notes = "글번호에 해당하는 문의글의 정보를 반환한다.", response = QuestionDto.class)
	@GetMapping("/{qno}")
	public ResponseEntity<QuestionDto> getQuestion(@PathVariable("qno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int qno) throws Exception {
		logger.info("getQuestion - 호출 : " + qno);
		return new ResponseEntity<QuestionDto>(qnaService.getQuestion(qno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "문의 글수정", notes = "새로운 문의글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateQuestion(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QuestionDto questionDto) throws Exception {
		logger.info("updateQuestion - 호출");
		
		if (qnaService.updateQuestion(questionDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "문의 글삭제", notes = "글번호에 해당하는 문의글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qno}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("qno") @ApiParam(value = "삭제할 글의 글번호.", required = true) int qno) throws Exception {
		logger.info("deleteQuestion - 호출");
		if (qnaService.deleteQuestion(qno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	/******* 답글 *******/	
	
	@ApiOperation(value = "답글 작성", notes = "새로운 답글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/answer")
	public ResponseEntity<String> registerAnswer(@RequestBody @ApiParam(value = "답글 정보.", required = true) AnswerDto answerDto) throws Exception {
		logger.info("registerAnswer - 호출");
		if (qnaService.registerAnswer(answerDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "답글보기", notes = "글번호에 해당하는 답글의 정보를 반환한다.", response = AnswerDto.class)
	@GetMapping("/answer/{qno}")
	public ResponseEntity<AnswerDto> getAnswer(@PathVariable("qno") @ApiParam(value = "얻어올 답글의 글번호.", required = true) int qno) throws Exception {
		logger.info("getAnswer - 호출 : " + qno);
		return new ResponseEntity<AnswerDto>(qnaService.getAnswer(qno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "답글수정", notes = "새로운 답글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/answer")
	public ResponseEntity<String> updateAnswer(@RequestBody @ApiParam(value = "수정할 답글 정보.", required = true) AnswerDto answerDto) throws Exception {
		logger.info("updateAnswer - 호출");
		
		if (qnaService.updateAnswer(answerDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "답글삭제", notes = "글번호에 해당하는 답글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/answer/{qno}")
	public ResponseEntity<String> deleteAnswer(@PathVariable("qno") @ApiParam(value = "삭제할 답글의 글번호.", required = true) int qno) throws Exception {
		logger.info("deleteAnswer - 호출");
		if (qnaService.deleteAnswer(qno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
