package com.example.board.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class BoardControllerTests {
//      마치 브라우저에서 URL을 요청한 것 처럼 환경을 만들어 준다.
    private MockMvc mockMvc;
    @Autowired
//      서버 환경 및 설정, 요청 등을 처리해주는 webApplicationContext를 불러온다.
    private WebApplicationContext webApplicationContext;

//      모든 @Test가 사직되기 전에 실행됨(매번)
//      @Test 메소드가 2개라면 두 번 실행된다.
    @BeforeEach
    public void setUP(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getListTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "1")
                .param("amount","10")
        ).andReturn().getModelAndView().getModelMap().toString());
    }


    @Test
    public void registerTest() throws Exception{
        log.info("추가된 게시글 번호 : " + mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("boardTitle","테스트 새 글 제목")
                .param("boardContent","테스트 새 글 내용")
                .param("boardWriter","Test")
        ).andReturn().getFlashMap());
    }

    @Test
    public void readTest() throws Exception{
        log.info("상세보기 : " + mockMvc.perform(MockMvcRequestBuilders.get("/board/read")
        .param("boardBno","3"))
        .andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void modifyTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("boardBno","3")
                .param("boardTitle","수정")
                .param("boardContent","사항")
                .param("boardWriter","입니다")
        ).andReturn().getModelAndView().getModelMap().toString());
    }


}