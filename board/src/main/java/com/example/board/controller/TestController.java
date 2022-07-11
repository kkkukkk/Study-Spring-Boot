package com.example.board.controller;

//임의의 controller, html, js 만들어서
//화면 쪽에 ajax로 데이터 쏴 보기

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.TestVO;
import com.example.board.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test/*")
public class TestController {
    public final TestService testService;


    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String register(@RequestBody TestVO testVO){
        testService.register(testVO);
        return "등록 성공";
    }

    @GetMapping("/{tno}")
    public TestVO read(@PathVariable("tno") Long testNumber){
        return testService.read(testNumber);
    }

    @GetMapping("/list/{page}")
    public List<TestVO> getList(@PathVariable("page") int pageNum){
        return testService.getList(new Criteria(pageNum, 10));
    }

    @DeleteMapping("/{tno}")
    public void delete(@PathVariable("tno") Long testNumber){

        for (int i = 0; i < 10; i++) {
            testService.remove(testNumber + i);
        }


    }

    @PatchMapping(value = {"/{tno}/{title}", "/{tno}/{content}", "/{tno}/{writer}", "/{tno}"},
            consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String modify(@PathVariable("tno") Long testNumber,
                         @PathVariable(value="title", required = false) String testTitle,
                         @PathVariable(value="content", required = false) String testContent,
                         @PathVariable(value="writer", required = false) String testWriter,
                         @RequestBody TestVO testVO){

        TestVO tVO = testService.read(testNumber);

        if(testVO.getTestWriter() == null){ // JSON 검증
            // replyWirter가 NULL 일 때 처리
            // 쿼리문으로 replyWriter를 가져와서 orElse()부분에 넣어주면 원래 Wirter 값을 가져올 수 있음
            testVO.setTestWriter(Optional.ofNullable(testWriter).orElse(tVO.getTestWriter())); // URI 검증
        }else if(testVO.getTestTitle() == null){
            testVO.setTestTitle(Optional.ofNullable(testTitle).orElse(tVO.getTestTitle()));
        }else if(testVO.getTestContent() == null){
            testVO.setTestContent(Optional.ofNullable(testContent).orElse(tVO.getTestContent()));
        }

        testVO.setTestNumber(testNumber);
        testService.modify(testVO);
        return "테스트 수정 성공";
    }



}
