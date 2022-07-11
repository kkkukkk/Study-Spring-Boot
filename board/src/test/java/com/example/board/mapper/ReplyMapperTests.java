package com.example.board.mapper;


import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    private Long[] arrs = {45273L,45274L,45275L,45276L,45277L};

    @Autowired
    private ReplyMapper replyMapper;

//    @Test
//    public void replyMapperTest(){
//        log.info(replyMapper + "");
//    }

    @Test
    public void insertTest(){

        //최근 5개의 게시글에 2개씩 댓글 달기
        //range : 마지막이 포함되지 않음
        //rangeClosed : 포함됨
        //IntStream : 정수형 stream
        //1부터 10까지 반복, foreach로 각각
        //5로 나눈 나머지를 인덱스에 넣어줌
        IntStream.rangeClosed(1, 10).forEach(i ->{
            ReplyVO replyVO = new ReplyVO();
            replyVO.setBoardBno(arrs[i % 5]);
            replyVO.setReplyContent("안녕하세요" + i);
            replyVO.setReplyWriter("박욱진이" + i);
            replyMapper.insert(replyVO);
        });

//        for (int i = 0; i < 2; i++) {
//            for (Long arr:arrs) {
//                ReplyVO replyVO = new ReplyVO();
//                replyVO.setBoardBno(arr);
//                replyVO.setReplyContent("안녕하세요");
//                replyVO.setReplyWriter("박욱진이");
//                replyMapper.insert(replyVO);
//            }
//        }
//        log.info("게시글 번호 : " + replyVO.getBoardBno());
//        log.info("댓글 번호 : " + replyVO.getReplyNumber());
    }

    @Test
    public void getDetailTest(){
        long replyNumber = 1L;
        ReplyVO detail = replyMapper.getDetail(replyNumber);
        log.info(detail.toString());
    }

    @Test
    public void deleteTest(){
        log.info("DELETE COUNT : " + replyMapper.delete(1l));
    }

    @Test
    public void updateTest(){
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyNumber(2L);
        replyVO.setBoardBno(45279L);
        replyVO.setReplyContent("수정된 댓글");
        log.info("UPDATE COUNT : " + replyMapper.update(replyVO));

    }

    @Test
    public void getListTest(){
        replyMapper.getList(new Criteria(), 45277L).stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void getTotalTest(){
        log.info("댓글 개수 : " + replyMapper.getTotal(77829L));
    }

}
