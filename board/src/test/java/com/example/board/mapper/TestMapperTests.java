package com.example.board.mapper;

import com.example.board.domain.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestMapperTests {
    @Autowired
    public TestMapper testMapper;

    @Test
    public void insertTest(){

        for (int i = 0; i < 500; i++) {
            TestVO testVO = new TestVO();
            testVO.setBoardBno(45100L);
            testVO.setTestTitle("테스트제목" + i);
            testVO.setTestContent("테스트내용" + i);
            testVO.setTestWriter("작성자" + i);
            testMapper.insert(testVO);
        }
    }

}
