package com.example.board.controller;
/*
REST (Representational State Transfer, 대표적인 상태 전송)

서버를 여러개 늘려야 하는데 서버 비용이 매우 큼
클라우드를 많이 사용함 (하나의 서버로 여러가지 서비스 제공)
하나의 프로그램을 제작하기 위해 하나의 프로젝트를 제작하는 것이 디폴트
클라우드 방식으로 서버를 사용하기 위해 여러가지 프로젝트 제작

REST 방식은 브라우저에 url을 입력하면 데이터를 띄워준다.
하나의 URI는 하나의 고유한 리소스(데이터)를 대표하도록 설계된다.
예) /board/123 : 게시글 중 123번 게시글의 전체 정보

*/



import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyPageDTO;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.processor.StandardReplaceTagProcessor;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

//RestController 는 ViewResolver가 관여하지 않음
@RestController
@Slf4j
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 등록
    // 브라우저에서 JSON타입으로 데이터를 전송하고 서버에서는
    // 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
    // consumes : 전달받은 데이터의 타입
    // @RequestBody : 전달받은 타입이 JSON타입으므로 그 값을 자동으로 객체 형태로 바꿔주는 어노테이션
    //                항상 써주어야 함 (전달받은 데이터를 알맞은 매개변수로 주입할 때 사용)
    // produces : 콜백함수로 결과를 전달할 때의 타입
    // ResponseEntity : 단순히 값만 리턴하는 것이 아닌 서버의 상태코드, 응답 메세지 등을 리턴할 수 있음 (이전 버전)
    @PostMapping(value = "/new", consumes="application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException{
        log.info("replyVO" + replyVO);
        replyService.comment(replyVO);
        // String을 Byte로 바꿔서 전송해야 한글이 깨지지 않음
        // HttpStatus.OK : AJAX 에서 success에 해당
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8") ,HttpStatus.OK);
    }

    //댓글 1개 조회
    //받아온 값을 rno라는 변수에 담겠다(replyNumber)
    @GetMapping("/{rno}")
    //@PathVariable 경로에 있는 값이 rno일 경우 오른쪽 변수에 그 값을 넣어준다.
    public ReplyVO read(@PathVariable("rno") Long replyNumber){
        log.info("read........" + replyNumber);
        return replyService.readOne(replyNumber);
    }

    // 댓글 전체 목록 조회
    @GetMapping("/list/{bno}/{page}")
    public ReplyPageDTO getList(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno){
        log.info("criteria : " + pageNum);
        log.info("boardBno : " + boardBno);
        return new ReplyPageDTO(replyService.getList(new Criteria(pageNum, 10), boardBno), replyService.getTotal(boardBno));
    }

    // 댓글 삭제
    @DeleteMapping("/{rno}")
    public String remove(@PathVariable("rno") Long replyNumber){
        replyService.remove(replyNumber);
        return "삭제 완료";
    }

    // 댓글 수정
    // PUT : 자원의 전체 수정, 자원 내 모든 필드를 전달 해야 함, 일부만 전달할 경우 오류
    // PATCH : 자원의 일부 수정, 수정할 필드만 전송(자동 주입이 아닌 부분만 수정 하는 쿼리문에서 사용)
    // 헤더와 url로 동시에 받을 경우 value 와 consumes 둘 다 작성
    // replyWriter 까지 수정 해야 할 경우

    // PATCH PUT 동시 처리할 때
//    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    @PatchMapping(value = {"/{rno}/{writer}", "/{rno}"}, consumes = "application/json")
    public String modify(@PathVariable("rno") Long replyNumber, @PathVariable(value="writer", required = false) String replyWriter, @RequestBody ReplyVO replyVO){
        log.info("modify..... : " + replyVO);
        log.info("modify..... : " + replyNumber);

        if(replyVO.getReplyWriter() == null){ // JSON 검증
            // replyWirter가 NULL 일 때 처리
            // 쿼리문으로 replyWriter를 가져와서 orElse()부분에 넣어주면 원래 Wirter 값을 가져올 수 있음
            replyVO.setReplyWriter(Optional.ofNullable(replyWriter).orElse("annoymous")); // URI 검증
        }
        replyVO.setReplyNumber(replyNumber);
        replyService.modify(replyVO);
        return "댓글 수정 성공";
    }

    //5개
    //1번 매개변수 없고 리턴은 문자열
    //2번 매개변수 1개 있고 리턴은 문자열
    //3번 매개변수 없고 리턴은 JSON Object
    //4번 매개변수 여러 개 있고 리턴은 JSON Object
    //5번 매개변수 여러 개 있고 리턴은 JSON Array

    //4개(DB 테이블 생성)
    //1번 매개변수 1개 있고 리턴은 문자열
    //2번 매개변수 없고 리턴은 JSON Object
    //3번 매개변수 여러 개 있고 리턴은 JSON Object
    //4번 매개변수 여러 개 있고 리턴은 JSON Array

    @GetMapping("/test")
    public String test(){
        return "안녕하세요";
    }

    @PostMapping("/test1/{rno}")
    public String test1(@PathVariable("rno") Long replyNumber){
        replyService.remove(replyNumber);
        return "" +  replyNumber + "번 댓글 삭제 성공!";
    }

    @GetMapping("/test2")
    public ReplyVO test2(){
        Long a = 89L;
        return replyService.readOne(a);
    }

    @PatchMapping(value = {"/test3/{rno}/{writer}"}, consumes = "application/json")
    public ReplyVO test3(@PathVariable("rno") Long replyNumber, @PathVariable(value="writer", required = false) String replyWriter,
                         @RequestBody ReplyVO replyVO){
        if(replyVO.getReplyWriter() == null){ // JSON 검증
            // replyWirter가 NULL 일 때 처리
            // 쿼리문으로 replyWriter를 가져와서 orElse()부분에 넣어주면 원래 Wirter 값을 가져올 수 있음
            replyVO.setReplyWriter(Optional.ofNullable(replyWriter).orElse("annoymous")); // URI 검증
        }
        replyVO.setReplyNumber(replyNumber);
        replyService.modify(replyVO);
        return replyService.readOne(replyVO.getReplyNumber());
    }

    @PostMapping(value = {"/test4/{bno}/{page}"}, consumes = "application/json")
    public List<ReplyVO> test4(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno){
        return replyService.getList(new Criteria(pageNum, 10), boardBno);
    }
}
